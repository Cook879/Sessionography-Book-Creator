package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.SongPerson;
import uk.me.richardcook.sinatra.generator.model.SongPersonJoined;
import uk.me.richardcook.sinatra.generator.service.SongPersonService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/song_person" )
public class SongPersonController {

	@Autowired
	private SongPersonService songPersonService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public SongPerson get( @PathVariable int id ) {
		return songPersonService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<SongPerson> getAll() {
		return songPersonService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody SongPerson songPerson ) {
		ResponseEntity responseEntity = songPersonService.validate( songPerson );
		if ( responseEntity != null )
			return responseEntity;

		songPersonService.save( songPerson );
		SongPerson songPerson1 = songPersonService.find( songPerson.getId() );
		if ( songPerson1 != null )
			return songPerson1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody SongPerson songPerson ) {
		if ( songPerson.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		SongPerson songPerson1 = songPersonService.find( id );
		if ( songPerson1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( songPerson.equals( songPerson1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = songPersonService.validate( songPerson );
		if ( responseEntity != null )
			return responseEntity;

		songPersonService.update( songPerson );

		SongPerson songPerson2 = songPersonService.find( id );
		if ( songPerson2.equals( songPerson ) )
			return songPerson2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/song/{id}", method = RequestMethod.GET )
	public List<SongPersonJoined> getForSong( @PathVariable int id ) {
		return songPersonService.findForSong( id );
	}

	@RequestMapping( value = "/song/{id}", method = RequestMethod.DELETE )
	public Object deleteForSong( @PathVariable int id ) {
		songPersonService.deleteForSong( id );
		return new ResponseEntity( "Deleted all people details for song " + id, HttpStatus.OK );
	}
}
