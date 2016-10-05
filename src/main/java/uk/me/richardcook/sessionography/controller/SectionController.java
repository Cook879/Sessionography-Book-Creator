package uk.me.richardcook.sessionography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sessionography.model.Section;
import uk.me.richardcook.sessionography.service.SectionService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/section" )
public class SectionController {

	@Autowired
	private SectionService sectionService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public Section get( @PathVariable int id ) {
		return sectionService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Section> getAll() {
		return sectionService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Section section ) {
		ResponseEntity responseEntity = sectionService.validate( section );
		if ( responseEntity != null )
			return responseEntity;

		sectionService.save( section );

		// Let's check it saved
		Section section1 = sectionService.find( section.getId() );
		if ( section1 != null )
			return section1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Section section ) {
		if ( section.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Section section1 = sectionService.find( id );
		if ( section1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( section.equals( section1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = sectionService.validate( section );
		if ( responseEntity != null )
			return responseEntity;

		sectionService.update( section );

		Section section2 = sectionService.find( id );
		if ( section2.equals( section ) )
			return section2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
