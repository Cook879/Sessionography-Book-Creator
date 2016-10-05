package uk.me.richardcook.sessionography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sessionography.model.ForOther;
import uk.me.richardcook.sessionography.service.ForOtherService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/for_other" )
public class ForOtherController {

	@Autowired
	private ForOtherService forOtherService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public ForOther get( @PathVariable int id ) {
		return forOtherService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<ForOther> getAll() {
		return forOtherService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody ForOther forOther ) {
		ResponseEntity responseEntity = forOtherService.validate( forOther );
		if ( responseEntity != null )
			return responseEntity;

		forOtherService.save( forOther );

		// Let's check it saved
		ForOther forOther1 = forOtherService.find( forOther.getId() );
		if ( forOther1 != null )
			return forOther1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody ForOther forOther ) {
		if ( forOther.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		ForOther forOther1 = forOtherService.find( id );
		if ( forOther1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( forOther.equals( forOther1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = forOtherService.validate( forOther );
		if ( responseEntity != null )
			return responseEntity;

		forOtherService.update( forOther );

		ForOther forOther2 = forOtherService.find( id );
		if ( forOther2.equals( forOther ) )
			return forOther2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
