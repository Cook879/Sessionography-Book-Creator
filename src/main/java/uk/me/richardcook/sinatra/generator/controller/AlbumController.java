package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.Album;
import uk.me.richardcook.sinatra.generator.model.AlbumJoined;
import uk.me.richardcook.sinatra.generator.service.AlbumService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/album" )
public class AlbumController {

	@Autowired
	private AlbumService albumService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public AlbumJoined get( @PathVariable int id ) {
		return albumService.findJoined( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Album> getAll() {
		return albumService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Album album ) {
		ResponseEntity responseEntity = albumService.validate( album );
		if ( responseEntity != null )
			return responseEntity;

		albumService.save( album );

		// Let's check it saved
		Album album1 = albumService.find( album.getId() );
		if ( album1 != null )
			return album1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Album album ) {
		if ( album.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Album album1 = albumService.find( id );
		if ( album1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( album.equals( album1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = albumService.validate( album );
		if ( responseEntity != null )
			return responseEntity;

		albumService.update( album );

		Album album2 = albumService.find( id );
		if ( album2.equals( album ) )
			return album2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
