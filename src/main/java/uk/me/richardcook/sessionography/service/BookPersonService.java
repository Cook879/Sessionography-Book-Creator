package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.PersonDao;
import uk.me.richardcook.sessionography.dao.PersonRoleDao;
import uk.me.richardcook.sessionography.model.*;

import java.util.List;
import java.util.Map;

@Service
public class BookPersonService {

	static final int PERSON_MUSICIAN = 0;
	static final int PERSON_ARRANGER = 1;
	static final int PERSON_WRITER = 2;
	static final int PERSON_GROUP = 3;

	@Autowired
	private PersonDao personDao;

	@Autowired
	private PersonRoleDao personRoleDao;

	@Autowired
	private BookSessionSongService bookSessionSongService;

	@Autowired
	private BookSongService bookSongService;

	public void generatePersons( BookFile book, int type ) {
		List<Person> persons = personDao.findAll();

		Character currentLetter = null;

		Map<Integer, SessionSongJoined> sessionSongs = bookSessionSongService.findAllMap();
		boolean group = false;
		boolean arranger = false;

		if ( type == PERSON_GROUP )
			group = true;
		if ( type == PERSON_ARRANGER )
			arranger = true;

		for ( Person person : persons ) {

			String firstName = person.getFirstName();
			String lastName = person.getLastName();

			List<SessionSongPersonDetails> sessionSongPersonDetailses = personRoleDao.findForPerson( person.getId(), group, arranger );

			if ( sessionSongPersonDetailses.size() == 0 )
				continue;

			String lastNameCaps = lastName.toUpperCase();
			if ( currentLetter == null || lastNameCaps.charAt( 0 ) != currentLetter ) {
				currentLetter = lastNameCaps.charAt( 0 );
				if ( currentLetter != 'A' ) {
					book.printEndSuperTabular();
					book.printEndFootnoteSize();
				}
				book.printSection( currentLetter.toString() );
				book.printBeginFootnoteSize();
				book.printSuperTabular( 3 );
			}

			if ( type == PERSON_GROUP ) {
				if ( firstName != null )
					book.printMultiColumn( 3, BookFile.createBoldText( firstName + " " + lastName ) );
				else
					book.printMultiColumn( 3, BookFile.createBoldText( lastName ) );
			} else {
				if ( firstName != null )
					book.printMultiColumn( 3, BookFile.createBoldText( lastName + ", " + firstName ) );
				else
					book.printMultiColumn( 3, BookFile.createBoldText( lastName ) );
			}

			for ( SessionSongPersonDetails sessionSongPersonDetails : sessionSongPersonDetailses ) {
				book.printTabJoin( BookFile.dateFormat.format( sessionSongPersonDetails.getDate() ),
						sessionSongs.get( sessionSongPersonDetails.getSessionSong() ).toShortString(book),
						sessionSongPersonDetails.getRole() );
			}

		}

		book.printEndSuperTabular();
		book.printEndFootnoteSize();
	}

	public void generateSongPersons( BookFile book ) {
		List<Person> persons = personDao.findAll();

		Character currentLetter = null;

		Map<Integer, Song> songs = bookSongService.findAllMap();

		for ( Person person : persons ) {

			String firstName = person.getFirstName();
			String lastName = person.getLastName();

			List<SongPersonDetails> songPersonDetailses = personRoleDao.findSongForPerson( person.getId() );

			if ( songPersonDetailses.size() == 0 )
				continue;

			String lastNameCaps = lastName.toUpperCase();
			if ( currentLetter == null || lastNameCaps.charAt( 0 ) != currentLetter ) {
				currentLetter = lastNameCaps.charAt( 0 );
				if ( currentLetter != 'A' ) {
					book.printEndSuperTabular();
					book.printEndFootnoteSize();
				}
				book.printSection( currentLetter.toString() );
				book.printBeginFootnoteSize();
				book.printSuperTabular( 3 );
			}

			if ( firstName != null )
				book.printMultiColumn( 3, BookFile.createBoldText( lastName + ", " + firstName ) );
			else
				book.printMultiColumn( 3, BookFile.createBoldText( lastName ) );


			for ( SongPersonDetails songPersonDetails : songPersonDetailses ) {
				Song song = songs.get( songPersonDetails.getSong() );
				book.printTabJoin( song.getYear(), song.getShortTitle(), songPersonDetails.getRole() );
			}

		}

		book.printEndSuperTabular();
		book.printEndFootnoteSize();
	}
}
