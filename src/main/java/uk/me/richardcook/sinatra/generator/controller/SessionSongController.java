package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.SessionSong;
import uk.me.richardcook.sinatra.generator.service.SessionSongService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/session_song" )
public class SessionSongController {

	@Autowired
	private SessionSongService sessionSongService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public SessionSong get( @PathVariable int id ) {
		return sessionSongService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<SessionSong> getAll() {
		return sessionSongService.findAll();
	}

	@RequestMapping( value = "/session/{id}", method = RequestMethod.GET )
	public List<SessionSong> getForSession( @PathVariable int id ) {
		return sessionSongService.findForSession( id );
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody SessionSong sessionSong ) {
		ResponseEntity responseEntity = sessionSongService.validate( sessionSong );
		if ( responseEntity != null )
			return responseEntity;

		sessionSongService.save( sessionSong );
		// Let's check it saved
		SessionSong sessionSong1 = sessionSongService.find( sessionSong.getId() );
		if ( sessionSong1 != null )
			return sessionSong1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody SessionSong sessionSong ) {
		if ( sessionSong.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		SessionSong sessionSong1 = sessionSongService.find( id );
		if ( sessionSong1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( sessionSong.equals( sessionSong1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = sessionSongService.validate( sessionSong );
		if ( responseEntity != null )
			return responseEntity;

		sessionSongService.update( sessionSong );

		SessionSong sessionSong2 = sessionSongService.find( id );
		if ( sessionSong2.equals( sessionSong ) )
			return sessionSong2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	public ResponseEntity delete( @PathVariable int id ) {
		sessionSongService.delete( id );
		return new ResponseEntity( "Deleted session song " + id, HttpStatus.OK );
	}
}
