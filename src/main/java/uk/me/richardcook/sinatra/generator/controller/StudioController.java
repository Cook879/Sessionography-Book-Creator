package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.Studio;
import uk.me.richardcook.sinatra.generator.service.StudioService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/studio" )
public class StudioController {

	@Autowired
	private StudioService studioService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public Studio get( @PathVariable int id ) {
		return studioService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Studio> getAll() {
		return studioService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Studio studio ) {
		ResponseEntity responseEntity = studioService.validate( studio );
		if ( responseEntity != null )
			return responseEntity;

		studioService.save( studio );
		// Let's check it saved
		Studio studio1 = studioService.find( studio.getId() );
		if ( studio1 != null )
			return studio1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Studio studio ) {
		if ( studio.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Studio studio1 = studioService.find( id );
		if ( studio1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( studio.equals( studio1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = studioService.validate( studio );
		if ( responseEntity != null )
			return responseEntity;

		studioService.update( studio );

		Studio studio2 = studioService.find( id );
		if ( studio2.equals( studio ) )
			return studio2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
