package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.Television;
import uk.me.richardcook.sinatra.generator.model.TelevisionJoined;
import uk.me.richardcook.sinatra.generator.service.TelevisionService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/television" )
public class TelevisionController {

	@Autowired
	private TelevisionService televisionService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public TelevisionJoined get( @PathVariable int id ) {
		return televisionService.findJoined( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Television> getAll() {
		return televisionService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Television television ) {
		ResponseEntity responseEntity = televisionService.validate( television );
		if ( responseEntity != null )
			return responseEntity;

		televisionService.save( television );

		// Let's check it saved
		Television television1 = televisionService.find( television.getId() );
		if ( television1 != null )
			return television1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Television television ) {
		if ( television.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Television television1 = televisionService.find( id );
		if ( television1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( television.equals( television1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = televisionService.validate( television );
		if ( responseEntity != null )
			return responseEntity;

		televisionService.update( television );

		Television television2 = televisionService.find( id );
		if ( television2.equals( television ) )
			return television2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
