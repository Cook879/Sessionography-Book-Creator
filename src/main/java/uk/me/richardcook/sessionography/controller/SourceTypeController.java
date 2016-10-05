package uk.me.richardcook.sessionography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sessionography.model.SourceType;
import uk.me.richardcook.sessionography.service.SourceTypeService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/source_type" )
public class SourceTypeController {

	@Autowired
	private SourceTypeService sourceTypeService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public SourceType get( @PathVariable int id ) {
		return sourceTypeService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<SourceType> getAll() {
		return sourceTypeService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody SourceType sourceType ) {
		ResponseEntity responseEntity = sourceTypeService.validate( sourceType );
		if ( responseEntity != null )
			return responseEntity;

		sourceTypeService.save( sourceType );
		// Let's check it saved

		SourceType sourceType1 = sourceTypeService.find( sourceType.getId() );
		if ( sourceType1 != null )
			return sourceType1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody SourceType sourceType ) {
		if ( sourceType.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		SourceType sourceType1 = sourceTypeService.find( id );
		if ( sourceType1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( sourceType.equals( sourceType1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = sourceTypeService.validate( sourceType );
		if ( responseEntity != null )
			return responseEntity;

		sourceTypeService.update( sourceType );

		SourceType sourceType2 = sourceTypeService.find( id );
		if ( sourceType2.equals( sourceType ) )
			return sourceType2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
