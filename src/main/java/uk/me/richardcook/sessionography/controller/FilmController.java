package uk.me.richardcook.sessionography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sessionography.model.Film;
import uk.me.richardcook.sessionography.model.FilmJoined;
import uk.me.richardcook.sessionography.service.FilmService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/film" )
public class FilmController {

	@Autowired
	private FilmService filmService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public FilmJoined get( @PathVariable int id ) {
		return filmService.findJoined( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Film> getAll() {
		return filmService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Film film ) {
		ResponseEntity responseEntity = filmService.validate( film );
		if ( responseEntity != null )
			return responseEntity;

		filmService.save( film );

		// Let's check it saved
		Film film1 = filmService.find( film.getId() );
		if ( film1 != null )
			return film1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Film film ) {
		if ( film.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Film film1 = filmService.find( id );
		if ( film1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( film.equals( film1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = filmService.validate( film );
		if ( responseEntity != null )
			return responseEntity;

		filmService.update( film );

		Film film2 = filmService.find( id );
		if ( film2.equals( film ) )
			return film2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
