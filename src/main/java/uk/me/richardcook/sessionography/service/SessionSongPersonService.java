package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.SessionSongPersonDao;
import uk.me.richardcook.sessionography.model.SessionSongPerson;
import uk.me.richardcook.sessionography.model.SessionSongPersonJoined;

import java.util.List;


@Service
public class SessionSongPersonService {

	@Autowired
	private SessionSongPersonDao sessionSongPersonDao;

	@Autowired
	private SessionSongService sessionSongService;

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PersonRoleNoteService personRoleNoteService;

	public List<SessionSongPerson> findAll() {
		return sessionSongPersonDao.findAll();
	}

	public SessionSongPerson find( int id ) {
		return sessionSongPersonDao.find( id );
	}

	public void save( SessionSongPerson sessionSongPerson ) {
		sessionSongPersonDao.save( sessionSongPerson );
	}

	public void update( SessionSongPerson sessionSongPerson ) {
		sessionSongPersonDao.update( sessionSongPerson );
	}

	public ResponseEntity validate( SessionSongPerson sessionSongPerson ) {
		// SessionView song can't be null
		if ( sessionSongPerson.getSessionSong() == null )
			return new ResponseEntity( "You must provide a session song", HttpStatus.BAD_REQUEST );
		// Person can't be null
		if ( sessionSongPerson.getPerson() == null )
			return new ResponseEntity( "You must provide a person", HttpStatus.BAD_REQUEST );
		// RoleView can't be null
		if ( sessionSongPerson.getRole() == null )
			return new ResponseEntity( "You must provide a role", HttpStatus.BAD_REQUEST );

		// SessionView song must be a legit session song id
		if ( sessionSongService.find( sessionSongPerson.getSessionSong() ) == null )
			return new ResponseEntity( "The session song you provided is not valid", HttpStatus.BAD_REQUEST );
		// Person must be a legit person id
		if ( personService.find( sessionSongPerson.getPerson() ) == null )
			return new ResponseEntity( "The person you provided is not valid", HttpStatus.BAD_REQUEST );
		// RoleView must be a legit role id
		if ( roleService.find( sessionSongPerson.getRole() ) == null )
			return new ResponseEntity( "The role you provided is not valid", HttpStatus.BAD_REQUEST );
		// Note must be a legit note id
		if ( sessionSongPerson.getNote() != null && personRoleNoteService.find( sessionSongPerson.getNote() ) == null )
			return new ResponseEntity( "The note you provided is not valid", HttpStatus.BAD_REQUEST );

		// SessionView song, person and role combo must be unique
		// TODO check this - probably won't work
		SessionSongPerson sessionSongPerson1 = sessionSongPersonDao.findBySessionSongPersonRole( sessionSongPerson.getSessionSong(), sessionSongPerson.getPerson(), sessionSongPerson.getRole() );
		if ( sessionSongPerson1 != null && ( ! sessionSongPerson.getSessionSong().equals( sessionSongPerson1.getSessionSong() ) ||
				                                     ! sessionSongPerson.getPerson().equals( sessionSongPerson1.getPerson() ) || ! sessionSongPerson.getRole().equals( sessionSongPerson1.getRole() ) ) )
			return new ResponseEntity( "The combination of session song, person and role must be unique", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<SessionSongPersonJoined> findForSession( int id ) {
		return sessionSongPersonDao.findForSession( sessionSongService.getIdsForSession( id ) );
	}

	public void deleteForSession( int id ) {
		sessionSongPersonDao.deleteForSession( sessionSongService.getIdsForSession( id ) );
	}
}
