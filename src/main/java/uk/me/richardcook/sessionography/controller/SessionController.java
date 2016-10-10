package uk.me.richardcook.sessionography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sessionography.model.Session;
import uk.me.richardcook.sessionography.model.SessionJoined;
import uk.me.richardcook.sessionography.service.SessionService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/session" )
public class SessionController {

	@Autowired
	private SessionService sessionService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public SessionJoined get( @PathVariable int id ) {
		return sessionService.findJoined( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Session> getAll() {
		return sessionService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Session session ) {
		ResponseEntity responseEntity = sessionService.validate( session );
		if ( responseEntity != null )
			return responseEntity;

		sessionService.save( session );

		// Let's check it saved
		Session session1 = sessionService.find( session.getId() );
		if ( session1 != null )
			return session1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Session session ) {
		if ( session.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Session session1 = sessionService.find( id );
		if ( session1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( session.equals( session1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = sessionService.validate( session );
		if ( responseEntity != null )
			return responseEntity;

		sessionService.update( session );

		Session session2 = sessionService.find( id );
		if ( session2.equals( session ) )
			return session2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
