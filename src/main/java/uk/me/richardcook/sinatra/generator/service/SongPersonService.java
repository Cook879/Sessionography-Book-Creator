package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.SongPersonDao;
import uk.me.richardcook.sinatra.generator.model.SongPerson;
import uk.me.richardcook.sinatra.generator.model.SongPersonJoined;

import java.util.List;


@Service
public class SongPersonService {

	@Autowired
	private SongPersonDao songPersonDao;

	@Autowired
	private SongService songService;

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PersonRoleNoteService personRoleNoteService;

	public List<SongPerson> findAll() {
		return songPersonDao.findAll();
	}

	public SongPerson find( int id ) {
		return songPersonDao.find( id );
	}

	public void save( SongPerson songPerson ) {
		songPersonDao.save( songPerson );
	}

	public void update( SongPerson songPerson ) {
		songPersonDao.update( songPerson );
	}

	public ResponseEntity validate( SongPerson songPerson ) {
		// Song can't be null
		if ( songPerson.getSong() == null )
			return new ResponseEntity( "You must provide a song", HttpStatus.BAD_REQUEST );
		// Person can't be null
		if ( songPerson.getPerson() == null )
			return new ResponseEntity( "You must provide a person", HttpStatus.BAD_REQUEST );
		// RoleView can't be null
		if ( songPerson.getRole() == null )
			return new ResponseEntity( "You must provide a role", HttpStatus.BAD_REQUEST );

		// Song must be a legit song id
		if ( songService.find( songPerson.getSong() ) == null )
			return new ResponseEntity( "The session song you provided is not valid", HttpStatus.BAD_REQUEST );
		// Person must be a legit person id
		if ( personService.find( songPerson.getPerson() ) == null )
			return new ResponseEntity( "The person you provided is not valid", HttpStatus.BAD_REQUEST );
		// RoleView must be a legit role id
		if ( roleService.find( songPerson.getRole() ) == null )
			return new ResponseEntity( "The role you provided is not valid", HttpStatus.BAD_REQUEST );
		// Note must be a legit note id
		if ( songPerson.getNote() != null && personRoleNoteService.find( songPerson.getNote() ) == null )
			return new ResponseEntity( "The note you provided is not valid", HttpStatus.BAD_REQUEST );

		// SessionView song, person and role combo must be unique
		SongPerson songPerson1 = songPersonDao.findBySongPersonRole( songPerson.getSong(), songPerson.getPerson(), songPerson.getRole() );
		if ( songPerson1 != null && ( ! songPerson.getSong().equals( songPerson1.getSong() ) ||
				                              ! songPerson.getPerson().equals( songPerson1.getPerson() ) || ! songPerson.getRole().equals( songPerson1.getRole() ) ) )
			return new ResponseEntity( "The combination of session song, person and role must be unique", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<SongPersonJoined> findForSong( int id ) {
		return songPersonDao.findForSong( id );
	}

	public void deleteForSong( int id ) {
		songPersonDao.deleteForSong( id );
	}
}
