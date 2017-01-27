package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.SessionSongDao;
import uk.me.richardcook.sinatra.generator.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookSessionSongService {

	@Autowired
	private SessionSongDao sessionSongDao;

	@Autowired
	private BookPersonRoleService bookPersonRoleService;

	public List<SessionSong> findForSession( int id ) {
		return sessionSongDao.findForSession( id );
	}

	public List<SessionSongJoined> findForSessionJoined( int id ) {
		return sessionSongDao.findForSessionJoined( id );
	}

	public List<Integer> findIdsForSession( int id ) {
		return sessionSongDao.findIdsForSession( id );
	}

	public void writeForSession( BookFile book, int id ) {
		List<SessionSongJoined> sessionSongs = findForSessionJoined( id );

		if ( sessionSongs.size() == 0 )
			return;

		for ( SessionSongJoined sessionSong : sessionSongs ) {
			System.out.println( "Session song id " + sessionSong.getId() );
			List<SessionSongSongJoined> sessionSongSongs = sessionSong.getSessionSongSongs();

			sessionSong.toBookString( book.getBookString( "master" ), book );

			for ( int i = 0; i < sessionSongSongs.size(); i++ ) {
				SessionSongSongJoined sessionSongSong = sessionSongSongs.get( i );
				SongJoinedBook song = sessionSongSong.getSong();
				song.writeToBook( book, sessionSongSongs.size() > 1 );
				bookPersonRoleService.writeForSong( book, song.getId() );
				if ( i + 1 == sessionSongSongs.size() && sessionSongSongs.size() > 1 )
					book.printNewLine();
			}

			bookPersonRoleService.writeArrangers( book, sessionSong.getId(), sessionSong.getSession() );

			int takeCount = 0;
			for ( TakeJoinedBook take : sessionSong.getTakes() ) {
				takeCount++;
				take.writeToBook( book );
				if( takeCount != sessionSong.getTakes().size() )
					book.printNewLine();
			}

			String notes = sessionSong.getNotes();
			if ( notes != null && ! notes.equals( "" ) ) {
				book.printNewLine();
				book.printLabel( book.getBookString( "note" ), notes );
			}
		}
	}

	public Map<Integer, SessionSongJoined> findAllMap() {
		List<SessionSongJoined> sessionSongs = findAllJoined();
		Map<Integer, SessionSongJoined> map = new HashMap<Integer, SessionSongJoined>();
		for ( SessionSongJoined sessionSong : sessionSongs ) {
			map.put( sessionSong.getId(), sessionSong );
		}
		return map;
	}

	private List<SessionSongJoined> findAllJoined() {
		return sessionSongDao.findAllJoined();
	}
}
