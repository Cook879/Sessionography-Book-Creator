package uk.me.richardcook.sessionography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sessionography.model.BookString;
import uk.me.richardcook.sessionography.service.BookStringService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/book_string" )
public class BookStringController {

	@Autowired
	private BookStringService bookStringService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public BookString get( @PathVariable int id ) {
		return bookStringService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<BookString> getAll() {
		return bookStringService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody BookString bookString ) {
		ResponseEntity responseEntity = bookStringService.validate( bookString );
		if ( responseEntity != null )
			return responseEntity;

		bookStringService.save( bookString );
		// Let's check it saved
		BookString bookString1 = bookStringService.find( bookString.getId() );
		if ( bookString1 != null )
			return bookString1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody BookString bookString ) {
		if ( bookString.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		BookString bookString1 = bookStringService.find( id );
		if ( bookString1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( bookString.equals( bookString1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = bookStringService.validate( bookString );
		if ( responseEntity != null )
			return responseEntity;

		bookStringService.update( bookString );

		BookString bookString2 = bookStringService.find( id );
		if ( bookString2.equals( bookString ) )
			return bookString2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
