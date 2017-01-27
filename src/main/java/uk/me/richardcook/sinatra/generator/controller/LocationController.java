package uk.me.richardcook.sinatra.generator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sinatra.generator.model.Location;
import uk.me.richardcook.sinatra.generator.service.LocationService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/location" )
public class LocationController {

	@Autowired
	private LocationService locationService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public Location get( @PathVariable int id ) {
		return locationService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Location> getAll() {
		return locationService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Location location ) {
		ResponseEntity responseEntity = locationService.validate( location );
		if ( responseEntity != null )
			return responseEntity;

		locationService.save( location );
		// Let's check it saved
		Location location1 = locationService.find( location.getId() );
		if ( location1 != null )
			return location1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Location location ) {
		if ( location.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Location location1 = locationService.find( id );
		if ( location1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( location.equals( location1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = locationService.validate( location );
		if ( responseEntity != null )
			return responseEntity;

		locationService.update( location );

		Location location2 = locationService.find( id );
		if ( location2.equals( location ) )
			return location2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
