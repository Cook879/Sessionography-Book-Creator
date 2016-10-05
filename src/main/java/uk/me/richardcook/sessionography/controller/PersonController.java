package uk.me.richardcook.sessionography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sessionography.model.Person;
import uk.me.richardcook.sessionography.service.PersonService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/person" )
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public Person get( @PathVariable int id ) {
		return personService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Person> getAll() {
		return personService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Person person ) {
		ResponseEntity responseEntity = personService.validate( person );
		if ( responseEntity != null )
			return responseEntity;

		personService.save( person );

		// Let's check it saved
		Person person1 = personService.find( person.getId() );
		if ( person1 != null )
			return person1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Person person ) {
		if ( person.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Person person1 = personService.find( id );
		if ( person1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( person.equals( person1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = personService.validate( person );
		if ( responseEntity != null )
			return responseEntity;

		personService.update( person );

		Person person2 = personService.find( id );
		if ( person2.equals( person ) )
			return person2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
