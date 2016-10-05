package uk.me.richardcook.sessionography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sessionography.model.With;
import uk.me.richardcook.sessionography.service.WithService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/with" )
public class WithController {

	@Autowired
	private WithService withService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public With get( @PathVariable int id ) {
		return withService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<With> getAll() {
		return withService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody With with ) {
		ResponseEntity responseEntity = withService.validate( with );
		if ( responseEntity != null )
			return responseEntity;

		withService.save( with );
		// Let's check it saved
		With with1 = withService.find( with.getId() );
		if ( with1 != null )
			return with1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody With with ) {
		if ( with.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		With with1 = withService.find( id );
		if ( with1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( with.equals( with1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = withService.validate( with );
		if ( responseEntity != null )
			return responseEntity;

		withService.update( with );

		With with2 = withService.find( id );
		if ( with2.equals( with ) )
			return with2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
