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

			book.printBoldText( "Recordings" );
			book.printNewLine();
			book.printSuperTabular( 4 );
			book.printTabJoin( BookFile.createBoldText( book.getBookString( "date" ) ),
					BookFile.createBoldText( book.getBookString( "type" ) ),
					BookFile.createBoldText( book.getBookString( "chapter" ) ),
					BookFile.createBoldText( book.getBookString( "notes" ) ) );

			Map<Date, String> rows = new TreeMap<Date, String>();
			for ( SessionSongSong sessionSongSong : song.getSessionSongSongs() ) {
				SessionSongJoined sessionSong = sessionSongs.get( sessionSongSong.getSessionSong() );
				SessionView session = sessions.get( sessionSong.getSession() );
				Section section = sections.get( session.getSection() );
				String notes = "";
				if ( sessionSong.getSessionSongSongs().size() > 1 ) {
					notes = book.getBookString( "medley" );
				}
				rows.put( session.getDate(), BookFile.createTabJoin( BookFile.dateFormat.format( session.getDate() ), session.getType(), Integer.toString( section.getPosition() ), notes ) );
			}

			for ( Date date : rows.keySet() ) {
				book.println( BookFile.removeSpecialCharacters( rows.get( date ) ) );
			}

			book.printEndSuperTabular();
		}

	}

	private List<SongJoinedBook> findAllJoinedBook() {
		return songDao.findAllJoinedBook();
	}

}
