package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.SessionSongSongDao;
import uk.me.richardcook.sinatra.generator.model.SessionSongSong;
import uk.me.richardcook.sinatra.generator.model.SessionSongSongJoined;

import java.util.List;


@Service
public class SessionSongSongService {

	@Autowired
	private SessionSongSongDao sessionSongSongDao;

	@Autowired
	private SessionSongService sessionSongService;

	@Autowired
	private SongService songService;

	public List<SessionSongSong> findAll() {
		return sessionSongSongDao.findAll();
	}

	public SessionSongSong find( int id ) {
		return sessionSongSongDao.find( id );
	}

	public void save( SessionSongSong sessionSongSong ) {
		sessionSongSongDao.save( sessionSongSong );
	}

	public void update( SessionSongSong sessionSongSong ) {
		sessionSongSongDao.update( sessionSongSong );
	}

	public ResponseEntity validate( SessionSongSong sessionSongSong ) {
		// SessionView song can't be null
		if ( sessionSongSong.getSessionSong() == null )
			return new ResponseEntity( "You must provide a session song", HttpStatus.BAD_REQUEST );
		// Song can't be null
		if ( sessionSongSong.getSong() == null )
			return new ResponseEntity( "You must provide a song", HttpStatus.BAD_REQUEST );
		// Position can't be null
		if ( sessionSongSong.getPosition() == null )
			return new ResponseEntity( "You must provide a role", HttpStatus.BAD_REQUEST );

		// SessionView song must be a legit session song id
		if ( sessionSongService.find( sessionSongSong.getSessionSong() ) == null )
			return new ResponseEntity( "The session song you provided is not valid", HttpStatus.BAD_REQUEST );
		// Song must be a legit song id
		if ( songService.find( sessionSongSong.getSong() ) == null )
			return new ResponseEntity( "The person you provided is not valid", HttpStatus.BAD_REQUEST );

		// SessionView song and position combo must be unique
		SessionSongSong sessionSongSong1 = sessionSongSongDao.findBySessionSongPosition( sessionSongSong.getSessionSong(), sessionSongSong.getPosition() );
		if ( sessionSongSong1 != null && ( ! sessionSongSong.getSessionSong().equals( sessionSongSong1.getSessionSong() ) ||
				                                   ! sessionSongSong.getPosition().equals( sessionSongSong1.getPosition() ) ) )
			return new ResponseEntity( "The combination of session song and position must be unique", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<SessionSongSongJoined> findForSessionSong( int id ) {
		return sessionSongSongDao.findForSessionSong( id );
	}

	public void deleteForSessionSong( int id ) {
		sessionSongSongDao.deleteForSessionSong( id );
	}
}
