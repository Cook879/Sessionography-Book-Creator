package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.SessionSongDao;
import uk.me.richardcook.sessionography.model.SessionSong;

import java.util.List;

@Service
public class SessionSongService {

	@Autowired
	private SessionSongDao sessionSongDao;

	@Autowired
	private SessionService sessionService;

	public List<SessionSong> findAll() {
		return sessionSongDao.findAll();
	}

	public SessionSong find( int id ) {
		return sessionSongDao.find( id );
	}

	public void save( SessionSong sessionSong ) {
		sessionSongDao.save( sessionSong );
	}

	public void update( SessionSong sessionSong ) {
		sessionSongDao.update( sessionSong );
	}

	public ResponseEntity validate( SessionSong sessionSong ) {
		// The following can be null
		if ( sessionSong.getMaster() != null && sessionSong.getMaster().equals( "" ) )
			sessionSong.setMaster( null );

		if ( sessionSong.getNotes() != null && sessionSong.getNotes().equals( "" ) )
			sessionSong.setNotes( null );

		// SessionView can't be null
		if ( sessionSong.getSession() == null )
			return new ResponseEntity( "You must provide a session", HttpStatus.BAD_REQUEST );
		// Position can't be null
		if ( sessionSong.getPosition() == null )
			return new ResponseEntity( "You must provide a position", HttpStatus.BAD_REQUEST );

		// SessionView must be a legit session id
		if ( sessionService.find( sessionSong.getSession() ) == null )
			return new ResponseEntity( "The session you provided is not valid", HttpStatus.BAD_REQUEST );

		// SessionView and position combo must be unique
		// TODO check this
		SessionSong sessionSong1 = sessionSongDao.findBySessionPosition( sessionSong.getSession(), sessionSong.getPosition() );
		if ( sessionSong1 != null && sessionSong.getId() != sessionSong1.getId() )
			return new ResponseEntity( "The combination of position and session must be unique", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<SessionSong> findForSession( int id ) {
		return sessionSongDao.findForSession( id );
	}

	public List<Integer> getIdsForSession( int id ) {
		return sessionSongDao.getIdsForSession( id );
	}

	public void delete( int id ) {
		sessionSongDao.delete( id );
	}
}
