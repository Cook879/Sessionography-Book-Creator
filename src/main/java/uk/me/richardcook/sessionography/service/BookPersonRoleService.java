package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.PersonRoleDao;
import uk.me.richardcook.sessionography.dao.PersonRoleNoteDao;
import uk.me.richardcook.sessionography.dao.SessionSongDao;
import uk.me.richardcook.sessionography.model.*;

import java.util.*;


@Service( "bookPersonRoleService" )
public class BookPersonRoleService {

	public static final int PERSON_ROLE_SESSION_SONG = 0;
	public static final int PERSON_ROLE_SONG = 1;
	public static final int PERSON_ROLE_ARRANGER = 2;

	@Autowired
	private PersonRoleDao personRoleDao;

	@Autowired
	private SessionSongDao sessionSongDao;

	@Autowired
	private PersonRoleNoteDao bookPersonRoleNoteDao;

	public List<SessionSongPersonView> findForSession( int id ) {
		// Get session song ids for that session
		List<Integer> sessionSongIds = sessionSongDao.findIdsForSession( id );
		if ( sessionSongIds.size() == 0 )
			return null;
		return personRoleDao.findForSessionView( sessionSongIds );
	}

	public List<SongPersonJoinedBook> findForSong( int id ) {
		return personRoleDao.findForSongJoinedBook( id );
	}

	public List<SessionSongPersonView> findForSessionSong( int id ) {
		return personRoleDao.findForSessionSongView( id );
	}

	// id is session id if type == musician or arranger, song if type == song
	public Map<Integer, Integer> writeToBook( BookFile book, List<? extends PersonRole> personRoles, int type, int id ) {

		if ( personRoles == null )
			return null;

		Map<RoleView, Map<Integer, Person>> roleMap = new HashMap<RoleView, Map<Integer, Person>>();

		// First int is note id, second is number assigned to it
		Map<Integer, Integer> noteMap = getPairingsForNotesForSession( id );

		// Only used for type PERSON_ROLE_SESSION_SONG
		Set<Integer> positions = new HashSet<Integer>();

		List<SessionSong> sessionSongs = sessionSongDao.findForSession( id );

		// For each personRole we have
		for ( PersonRole personRole : personRoles ) {
			RoleView role = personRole.getRole();
			Person person = personRole.getPerson();

			// Add to our roleMap
			if ( ! roleMap.containsKey( role ) ) {
				roleMap.put( role, new HashMap<Integer, Person>() );
				roleMap.get( role ).put( person.getId(), person );
			} else if ( ! roleMap.get( role ).containsKey( person.getId() ) ) {
				roleMap.get( role ).put( person.getId(), person );
			} else {
				person = roleMap.get( role ).get( person.getId() );
			}

			// If a session song, we want to add it to the person
			if ( type == PERSON_ROLE_SESSION_SONG ) {
				if ( person.getCurrSession() == null || person.getCurrSession() != id ) {
					person.setCurrSession( id );
					person.resetRoleSessionSongs();
					person.setNote( null );
				}
				SessionSongPersonView sessionSongPerson = (SessionSongPersonView) personRole;
				person.addSessionSong( role, sessionSongPerson.getSessionSongPosition() );
				positions.add( sessionSongPerson.getSessionSongPosition() );
			}

			// If arranger, we want to reset the note and currSession
			if ( type == PERSON_ROLE_ARRANGER ) {
				if ( person.getCurrSession() == null || person.getCurrSession() != id ) {
					person.setCurrSession( id );
					person.resetRoleSessionSongs();
				}
				person.setNote( null );
			}

			// We want to store notes for future use
			Integer note = personRole.getNote();
			if ( note != null ) {
				person.setNote( noteMap.get( note ) );
			}
		}

		// Sort by role
		List<RoleView> roles = new ArrayList<RoleView>();
		roles.addAll( roleMap.keySet() );
		Collections.sort( roles, new RoleView.RoleComparator() );

		// Process for the book
		for ( RoleView role : roles ) {
			String labelContents = "";

			List<Person> people = Collections.list( Collections.enumeration( roleMap.get( role ).values() ) );
			Collections.sort( people, new Person.PersonSort() );

			for ( int i = 0; i < people.size(); i++ ) {
				Person person = people.get( i );
				String personString = person.toPersonnelString( role, sessionSongs.size(), type );

				if ( i == 0 ) {
					labelContents += personString;
				} else if ( i == people.size() - 1 && type != PERSON_ROLE_SESSION_SONG ) {
					labelContents += " and " + personString;
				} else {
					labelContents += ", " + personString;
				}
			}

			if ( type == PERSON_ROLE_SESSION_SONG )
				book.printItem( BookFile.createLabel( role.getName() + ":", labelContents ) );
			else {
				book.printLabel( role.getName() + ":", labelContents );
				book.printNewLine();
			}
		}
		return noteMap;
	}

	public void writeForSession( BookFile book, int id ) {
		List<SessionSongPersonView> sessionSongPersonViews = findForSession( id );

		if ( sessionSongPersonViews == null )
			return;

		book.printSubSubSection( book.getBookString( "personnel" ) );
		book.printBeginDescription();
		book.printSetLength( "\\parskip", "0pt" );
		book.printSetLength( "\\parsep", "0pt" );
		book.printSetLength( "\\itemsep", "1pt" );

		Map<Integer, Integer> noteMap = writeToBook( book, findForSession( id ), PERSON_ROLE_SESSION_SONG, id );

		if ( noteMap != null )
			writeNotes( book, noteMap );

		book.printEndDescription();
	}

	public void writeForSong( BookFile book, int id ) {
		writeToBook( book, findForSong( id ), PERSON_ROLE_SONG, id );
	}

	public void writeNotes( BookFile book, Map<Integer, Integer> noteMap ) {
		if ( noteMap.size() > 0 ) {
			book.printNewLine();
			book.printFootnoteSize();

			PersonRoleNote[] notes = new PersonRoleNote[ noteMap.size() ];
			for ( Object key : noteMap.keySet() ) {
				notes[ noteMap.get( key ) - 1 ] = bookPersonRoleNoteDao.find( (Integer) key );
			}

			int noteCount = 1;
			for ( PersonRoleNote note : notes ) {
				book.printNewLine();
				book.printSuperScriptText( Integer.toString( noteCount ) );
				book.println( BookFile.removeSpecialCharacters( note.toString() ) );
				noteCount++;
			}

		}
	}

	public void writeArrangers( BookFile book, int id, int session ) {
		writeToBook( book, findForSessionSong( id ), PERSON_ROLE_ARRANGER, session );
	}

	public Map<Integer, Integer> getPairingsForNotesForSession( int id ) {
		List<PersonRoleNote> personRoleNotes = bookPersonRoleNoteDao.findForSession( id );
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int i = 1;
		for ( PersonRoleNote personRoleNote : personRoleNotes ) {
			map.put( personRoleNote.getId(), i );
			i++;
		}
		return map;
	}

}
