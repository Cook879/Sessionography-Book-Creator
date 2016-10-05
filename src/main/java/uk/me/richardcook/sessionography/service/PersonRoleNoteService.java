package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.PersonRoleNoteDao;
import uk.me.richardcook.sessionography.model.PersonRoleNote;

import java.util.List;


@Service
public class PersonRoleNoteService {

	@Autowired
	private PersonRoleNoteDao personRoleNoteDao;

	public List<PersonRoleNote> findAll() {
		return personRoleNoteDao.findAll();
	}

	public PersonRoleNote find( int id ) {
		return personRoleNoteDao.find( id );
	}

	public void save( PersonRoleNote personRoleNote ) {
		personRoleNoteDao.save( personRoleNote );
	}

	public void update( PersonRoleNote personRoleNote ) {
		personRoleNoteDao.update( personRoleNote );
	}

	public ResponseEntity validate( PersonRoleNote personRoleNote ) {
		// Name can't be null
		if ( personRoleNote.getSession() == null || personRoleNote.getSession().equals( "" ) )
			return new ResponseEntity( "You must provide a session id", HttpStatus.BAD_REQUEST );

		if ( personRoleNote.getNote() == null || personRoleNote.getNote().equals( "" ) )
			return new ResponseEntity( "You must provide a note", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<PersonRoleNote> findForSession( int id ) {
		return personRoleNoteDao.findForSession( id );
	}
}
