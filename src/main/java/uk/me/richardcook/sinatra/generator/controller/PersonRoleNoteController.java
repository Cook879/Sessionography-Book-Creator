package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.PersonRoleNote;
import uk.me.richardcook.sinatra.generator.service.PersonRoleNoteService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/person_role_note" )
public class PersonRoleNoteController {

	@Autowired
	private PersonRoleNoteService personRoleNoteService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public PersonRoleNote get( @PathVariable int id ) {
		return personRoleNoteService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<PersonRoleNote> getAll() {
		return personRoleNoteService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody PersonRoleNote personRoleNote ) {
		ResponseEntity responseEntity = personRoleNoteService.validate( personRoleNote );
		if ( responseEntity != null )
			return responseEntity;

		personRoleNoteService.save( personRoleNote );

		// Let's check it saved
		PersonRoleNote personRoleNote1 = personRoleNoteService.find( personRoleNote.getId() );
		if ( personRoleNote1 != null )
			return personRoleNote1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody PersonRoleNote personRoleNote ) {
		if ( personRoleNote.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		PersonRoleNote personRoleNote1 = personRoleNoteService.find( id );
		if ( personRoleNote1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( personRoleNote.equals( personRoleNote1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = personRoleNoteService.validate( personRoleNote );
		if ( responseEntity != null )
			return responseEntity;

		personRoleNoteService.update( personRoleNote );

		PersonRoleNote personRoleNote2 = personRoleNoteService.find( id );

		if ( personRoleNote2.equals( personRoleNote ) )
			return personRoleNote2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
