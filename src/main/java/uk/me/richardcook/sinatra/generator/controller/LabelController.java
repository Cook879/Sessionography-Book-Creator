package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.Label;
import uk.me.richardcook.sinatra.generator.service.LabelService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/label" )
public class LabelController {

	@Autowired
	private LabelService labelService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public Label get( @PathVariable int id ) {
		return labelService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Label> getAll() {
		return labelService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Label label ) {
		ResponseEntity responseEntity = labelService.validate( label );
		if ( responseEntity != null )
			return responseEntity;

		labelService.save( label );
		// Let's check it saved

		Label label1 = labelService.find( label.getId() );
		if ( label1 != null )
			return label1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Label label ) {
		if ( label.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Label label1 = labelService.find( id );
		if ( label1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( label.equals( label1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = labelService.validate( label );
		if ( responseEntity != null )
			return responseEntity;

		labelService.update( label );

		Label label2 = labelService.find( id );
		if ( label2.equals( label ) )
			return label2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
