package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.SessionSongDao;
import uk.me.richardcook.sessionography.model.*;

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

		for ( SessionSongJoined sessionSong : sessionSongs ) {
			List<SessionSongSongJoined> sessionSongSongs = sessionSong.getSessionSongSongs();

			book.println( sessionSong.toBookString(book.getBookString( "master" )) );

			for ( SessionSongSongJoined sessionSongSong : sessionSongSongs ) {
				SongJoinedBook song = sessionSongSong.getSong();
				song.writeToBook( book, sessionSongSongs.size() > 1 );
				bookPersonRoleService.writeForSong( book, song.getId() );
			}
			bookPersonRoleService.writeArrangers( book, sessionSong.getId(), sessionSong.getSession() );

			for ( TakeJoinedBook take : sessionSong.getTakes() ) {
				take.writeToBook( book );
			}

			String notes = sessionSong.getNotes();
			if ( notes != null ) {
				book.printNewLine();
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
