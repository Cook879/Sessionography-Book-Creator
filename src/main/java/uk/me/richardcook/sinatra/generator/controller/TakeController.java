package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.Take;
import uk.me.richardcook.sinatra.generator.model.TakeJoined;
import uk.me.richardcook.sinatra.generator.service.TakeService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/take" )
public class TakeController {

	@Autowired
	private TakeService takeService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public TakeJoined get( @PathVariable int id ) {
		return takeService.findJoined( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Take> getAll() {
		return takeService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Take take ) {
		ResponseEntity responseEntity = takeService.validate( take );
		if ( responseEntity != null )
			return responseEntity;

		takeService.save( take );
		// Let's check it saved
		Take take1 = takeService.find( take.getId() );
		if ( take1 != null )
			return take1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Take take ) {
		if ( take.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Take take1 = takeService.find( id );
		if ( take1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( take.equals( take1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = takeService.validate( take );
		if ( responseEntity != null )
			return responseEntity;

		takeService.update( take );

		Take take2 = takeService.find( id );
		if ( take2.equals( take ) )
			return take2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/session_song/{id}", method = RequestMethod.GET )
	public List<TakeJoined> getForSessionSong( @PathVariable int id ) {
		return takeService.findForSessionSong( id );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	public ResponseEntity delete( @PathVariable int id ) {
		takeService.delete( id );
		return new ResponseEntity( "Deleted take " + id, HttpStatus.OK );
	}
}
