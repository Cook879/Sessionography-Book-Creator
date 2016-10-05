package uk.me.richardcook.sessionography.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.me.richardcook.sessionography.model.RoleGroup;
import uk.me.richardcook.sessionography.service.RoleGroupService;

import java.util.List;

@RestController
@RequestMapping( value = "/api/role_group" )
public class RoleGroupController {

	@Autowired
	private RoleGroupService roleGroupService;

	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	public RoleGroup get( @PathVariable int id ) {
		return roleGroupService.find( id );
	}

	@RequestMapping( value = "", method = RequestMethod.GET )
	public List<RoleGroup> getAll() {
		return roleGroupService.findAll();
	}

	@RequestMapping( value = "", method = RequestMethod.POST )
	public Object post( @RequestBody RoleGroup roleGroup ) {
		ResponseEntity responseEntity = roleGroupService.validate( roleGroup );
		if ( responseEntity != null )
			return responseEntity;

		roleGroupService.save( roleGroup );

		RoleGroup roleGroup1 = roleGroupService.find( roleGroup.getId() );
		if ( roleGroup1 != null )
			return roleGroup1;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}

	@RequestMapping( value = "/{id}", method = RequestMethod.PUT )
	public Object put( @PathVariable int id, @RequestBody RoleGroup roleGroup ) {
		if ( roleGroup.getId() != id )
			return new ResponseEntity( "Invalid request - URL id parameter should be the same as the id of the object", HttpStatus.BAD_REQUEST );

		RoleGroup roleGroup1 = roleGroupService.find( id );
		if ( roleGroup1 == null )
			return new ResponseEntity( "Error - trying to update an object which doesn't exist - bad id", HttpStatus.BAD_REQUEST );
		if ( roleGroup.equals( roleGroup1 ) )
			return new ResponseEntity( "Same data - no changes necessary", HttpStatus.OK );

		ResponseEntity responseEntity = roleGroupService.validate( roleGroup );
		if ( responseEntity != null )
			return responseEntity;

		roleGroupService.update( roleGroup );

		RoleGroup roleGroup2 = roleGroupService.find( id );
		if ( roleGroup2.equals( roleGroup ) )
			return roleGroup2;
		return new ResponseEntity( "Failed to submit - reason unknown", HttpStatus.BAD_REQUEST );
	}
}
