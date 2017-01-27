package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.SessionSongSong;
import uk.me.richardcook.sinatra.generator.model.SessionSongSongJoined;
import uk.me.richardcook.sinatra.generator.service.SessionSongSongService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/session_song_song" )
public class SessionSongSongController {

	@Autowired
	private SessionSongSongService sessionSongSongService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public SessionSongSong get( @PathVariable int id ) {
		return sessionSongSongService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<SessionSongSong> getAll() {
		return sessionSongSongService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody SessionSongSong sessionSongSong ) {
		ResponseEntity responseEntity = sessionSongSongService.validate( sessionSongSong );
		if ( responseEntity != null )
			return responseEntity;

		sessionSongSongService.save( sessionSongSong );
		SessionSongSong sessionSongSong1 = sessionSongSongService.find( sessionSongSong.getId() );
		if ( sessionSongSong1 != null )
			return sessionSongSong1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody SessionSongSong sessionSongSong ) {
		if ( sessionSongSong.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		SessionSongSong sessionSongSong1 = sessionSongSongService.find( id );
		if ( sessionSongSong1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( sessionSongSong.equals( sessionSongSong1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = sessionSongSongService.validate( sessionSongSong );
		if ( responseEntity != null )
			return responseEntity;

		sessionSongSongService.update( sessionSongSong );

		SessionSongSong sessionSongSong2 = sessionSongSongService.find( id );
		if ( sessionSongSong2.equals( sessionSongSong ) )
			return sessionSongSong2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/session_song/{id}", method = RequestMethod.GET )
	public List<SessionSongSongJoined> getForSessionSong( @PathVariable int id ) {
		return sessionSongSongService.findForSessionSong( id );
	}

	@RequestMapping( value = "/session_song/{id}", method = RequestMethod.DELETE )
	public Object deleteForSessionSong( @PathVariable int id ) {
		sessionSongSongService.deleteForSessionSong( id );
		return new ResponseEntity( "Deleted all song details for session " + id, HttpStatus.OK );
	}

}
