package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.Source;
import uk.me.richardcook.sinatra.generator.model.SourceJoined;
import uk.me.richardcook.sinatra.generator.service.SourceService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/source" )
public class SourceController {

	@Autowired
	private SourceService sourceService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public SourceJoined get( @PathVariable int id ) {
		return sourceService.findJoined( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Source> getAll() {
		return sourceService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Source source ) {
		ResponseEntity responseEntity = sourceService.validate( source );
		if ( responseEntity != null )
			return responseEntity;

		sourceService.save( source );
		// Let's check it saved
		Source source1 = sourceService.find( source.getId() );
		if ( source1 != null )
			return source1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Source source ) {
		if ( source.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Source source1 = sourceService.find( id );
		if ( source1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( source.equals( source1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = sourceService.validate( source );
		if ( responseEntity != null )
			return responseEntity;

		sourceService.update( source );

		Source source2 = sourceService.find( id );
		if ( source2.equals( source ) )
			return source2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
