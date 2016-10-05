package uk.me.richardcook.sessionography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sessionography.model.Radio;
import uk.me.richardcook.sessionography.model.RadioJoined;
import uk.me.richardcook.sessionography.service.RadioService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/radio" )
public class RadioController {

	@Autowired
	private RadioService radioService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public RadioJoined get( @PathVariable int id ) {
		return radioService.findJoined( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Radio> getAll() {
		return radioService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Radio radio ) {
		ResponseEntity responseEntity = radioService.validate( radio );
		if ( responseEntity != null )
			return responseEntity;

		radioService.save( radio );

		// Let's check it saved
		Radio radio1 = radioService.find( radio.getId() );
		if ( radio1 != null )
			return radio1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Radio radio ) {
		if ( radio.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Radio radio1 = radioService.find( id );
		if ( radio1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( radio.equals( radio1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = radioService.validate( radio );
		if ( responseEntity != null )
			return responseEntity;

		radioService.update( radio );

		Radio radio2 = radioService.find( id );
		if ( radio2.equals( radio ) )
			return radio2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
