package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.Release;
import uk.me.richardcook.sinatra.generator.model.ReleaseJoined;
import uk.me.richardcook.sinatra.generator.service.ReleaseService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/release" )
public class ReleaseController {

	@Autowired
	private ReleaseService releaseService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public ReleaseJoined get( @PathVariable int id ) {
		return releaseService.findJoined( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Release> getAll() {
		return releaseService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Release release ) {
		ResponseEntity responseEntity = releaseService.validate( release );
		if ( responseEntity != null )
			return responseEntity;

		releaseService.save( release );
		// Let's check it saved
		Release release1 = releaseService.find( release.getId() );
		if ( release1 != null )
			return new ResponseEntity( "Success - id is " + release.getId(), HttpStatus.CREATED );
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Release release ) {
		if ( release.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Release release1 = releaseService.find( id );
		if ( release1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( release.equals( release1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = releaseService.validate( release );
		if ( responseEntity != null )
			return responseEntity;

		releaseService.update( release );

		Release release2 = releaseService.find( id );
		if ( release2.equals( release ) )
			return release2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
