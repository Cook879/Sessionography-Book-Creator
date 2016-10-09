package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.SongDao;
import uk.me.richardcook.sessionography.model.*;

import java.util.*;

@Service
public class BookSongService {

	@Autowired
	private SongDao songDao;

	@Autowired
	private BookSessionSongService bookSessionSongService;

	@Autowired
	private BookPersonRoleService bookPersonRoleService;

	@Autowired
	private BookSessionService bookSessionService;

	@Autowired
	private BookSectionService bookSectionService;

	public List<Song> findAll() {
		return songDao.findAll();
	}

	public List<SongJoined> findAllJoined() {
		return songDao.findAllJoined();
	}

	public Map<Integer, Song> findAllMap() {
		List<Song> songs = findAll();
		Map<Integer, Song> map = new HashMap<Integer, Song>();
		for ( Song song : songs ) {
			map.put( song.getId(), song );
		}
		return map;

	}

	public void generateSongs( BookFile book ) {
		List<SongJoinedBook> songs = findAllJoinedBook();
		Map<Integer, SessionView> sessions = bookSessionService.findAllMap();
		Map<Integer, SessionSongJoined> sessionSongs = bookSessionSongService.findAllMap();
		Map<Integer, Section> sections = bookSectionService.findAllMap();

		Character currentLetter = null;

		for ( SongJoinedBook song : songs ) {

			String titleCaps = song.getShortTitle().toUpperCase();

			if ( currentLetter == null || titleCaps.charAt( 0 ) != currentLetter ) {
				currentLetter = titleCaps.charAt( 0 );
				book.printChapter( currentLetter.toString() );
			}

			book.printSection( song.getTitle() );
			song.writeToBook( book, false );
			bookPersonRoleService.writeForSong( book, song.getId() );
			book.printNewLine();

			book.printBoldText( "Recordings" );
			book.printNewLine();
			book.printLongTable( 'l', 4 );
			book.printTabJoin( BookFile.createBoldText( book.getBookString( "date" ) ),
					BookFile.createBoldText( book.getBookString( "type" ) ),
					BookFile.createBoldText( book.getBookString( "chapter" ) ),
					BookFile.createBoldText( book.getBookString( "notes" ) ) );

			Map<Date, Map<Integer, String>> rows = new TreeMap<Date, Map<Integer, String>>();
			for ( SessionSongSong sessionSongSong : song.getSessionSongSongs() ) {
				SessionSongJoined sessionSong = sessionSongs.get( sessionSongSong.getSessionSong() );
				SessionView session = sessions.get( sessionSong.getSession() );
				Section section = sections.get( session.getSection() );
				String notes = "";
				if ( sessionSong.getSessionSongSongs().size() > 1 ) {
					notes = book.getBookString( "medley" );
				}
				if( sessionSongSong.isParody() ) {
					if( !notes.equals( "" ) )
						notes += " / ";
					notes += book.getBookString( "parody" );
				}
				if( sessionSong.isReprise() ) {
					if( !notes.equals( "" ) )
						notes += " / ";
					notes += book.getBookString( "reprise" );
				}

				Date date = session.getDate();
				int position = sessionSong.getPosition();
				String tableRow = BookFile.createTabJoin( BookFile.dateFormat.format( session.getDate() ), session.getType(), Integer.toString( section.getPosition()+1 ), notes );
				if( rows.containsKey( date ) ) {
					rows.get(date).put( position, tableRow );
				} else {
					TreeMap<Integer, String> posMap = new TreeMap<Integer, String>();
					posMap.put(position, tableRow );
					rows.put( date, posMap );
				}
			}

			for ( Date date : rows.keySet() ) {
				Map<Integer, String> posMap = rows.get(date);
				for( Integer position : posMap.keySet() ) {
					book.println( posMap.get( position ) );
				}
			}

			book.printEndLongTable();
		}

	}

	private List<SongJoinedBook> findAllJoinedBook() {
		return songDao.findAllJoinedBook();
	}

}
