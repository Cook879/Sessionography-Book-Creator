package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.SessionType;
import uk.me.richardcook.sinatra.generator.service.SessionTypeService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/session_type" )
public class SessionTypeController {

	@Autowired
	private SessionTypeService sessionTypeService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public SessionType get( @PathVariable int id ) {
		return sessionTypeService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<SessionType> getAll() {
		return sessionTypeService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody SessionType sessionType ) {
		ResponseEntity responseEntity = sessionTypeService.validate( sessionType );
		if ( responseEntity != null )
			return responseEntity;

		sessionTypeService.save( sessionType );
		// Let's check it saved

		SessionType sessionType1 = sessionTypeService.find( sessionType.getId() );
		if ( sessionType1 != null )
			return sessionType1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody SessionType sessionType ) {
		if ( sessionType.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		SessionType sessionType1 = sessionTypeService.find( id );
		if ( sessionType1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( sessionType.equals( sessionType1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = sessionTypeService.validate( sessionType );
		if ( responseEntity != null )
			return responseEntity;

		sessionTypeService.update( sessionType );

		SessionType sessionType2 = sessionTypeService.find( id );
		if ( sessionType2.equals( sessionType ) )
			return sessionType2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
