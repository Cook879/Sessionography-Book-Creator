package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.Format;
import uk.me.richardcook.sinatra.generator.service.FormatService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/format" )
public class FormatController {

	@Autowired
	private FormatService formatService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public Format get( @PathVariable int id ) {
		return formatService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Format> getAll() {
		return formatService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Format format ) {
		ResponseEntity responseEntity = formatService.validate( format );
		if ( responseEntity != null )
			return responseEntity;

		formatService.save( format );

		Format format1 = formatService.find( format.getId() );
		if ( format1 != null )
			return format1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Format format ) {
		if ( format.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Format format1 = formatService.find( id );
		if ( format1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( format.equals( format1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = formatService.validate( format );
		if ( responseEntity != null )
			return responseEntity;

		formatService.update( format );

		Format format2 = formatService.find( id );
		if ( format2.equals( format ) )
			return format2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
