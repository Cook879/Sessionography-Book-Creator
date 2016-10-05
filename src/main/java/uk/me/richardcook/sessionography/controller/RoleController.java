package uk.me.richardcook.sessionography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sessionography.model.Role;
import uk.me.richardcook.sessionography.service.RoleService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/role" )
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public Role get( @PathVariable int id ) {
		return roleService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<Role> getAll() {
		return roleService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody Role role ) {
		ResponseEntity responseEntity = roleService.validate( role );
		if ( responseEntity != null )
			return responseEntity;

		roleService.save( role );
		// Let's check it saved
		Role role1 = roleService.find( role.getId() );
		if ( role1 != null )
			return role1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody Role role ) {
		if ( role.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		Role role1 = roleService.find( id );
		if ( role1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( role.equals( role1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = roleService.validate( role );
		if ( responseEntity != null )
			return responseEntity;

		roleService.update( role );

		Role role2 = roleService.find( id );
		if ( role2.equals( role ) )
			return role2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
