package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.Song;
import uk.me.richardcook.sinatra.generator.model.SongJoined;
import uk.me.richardcook.sinatra.generator.service.SongService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/song" )
public class SongController {

	@Autowired
	private SongService songService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public SongJoined get( @PathVariable int id ) {
		return songService.findJoined( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Song> getAll() {
		return songService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Song song ) {
		ResponseEntity responseEntity = songService.validate( song );
		if ( responseEntity != null )
			return responseEntity;

		songService.save( song );
		// Let's check it saved
		Song song1 = songService.find( song.getId() );
		if ( song1 != null )
			return song1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Song song ) {
		if ( song.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Song song1 = songService.find( id );
		if ( song1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( song.equals( song1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = songService.validate( song );
		if ( responseEntity != null )
			return responseEntity;

		songService.update( song );

		Song song2 = songService.find( id );
		if ( song2.equals( song ) )
			return song2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
