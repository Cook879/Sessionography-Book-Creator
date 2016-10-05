package uk.me.richardcook.sessionography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.me.richardcook.sessionography.service.BookCreationService;

@RestController
@RequestMapping( value = "/api/book_generator" )
public class BookGenerator {

	@Autowired
	private BookCreationService bookCreationService;

	@RequestMapping( value = "", method = RequestMethod.POST )
	public ResponseEntity main() {
		try {
			bookCreationService.createMainBook();
			bookCreationService.createPersonBook();
			bookCreationService.createSongBook();
			return new ResponseEntity( "Books successfully generated - you can find the .tex files in the same folder as this executable is", HttpStatus.OK );
		} catch ( Exception e ) {
			return new ResponseEntity( "An error has occured - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}

}
