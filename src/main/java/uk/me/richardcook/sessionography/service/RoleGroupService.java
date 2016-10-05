package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.RoleGroupDao;
import uk.me.richardcook.sessionography.model.RoleGroup;

import java.util.List;


@Service
public class RoleGroupService {

	@Autowired
	private RoleGroupDao roleGroupDao;

	public List<RoleGroup> findAll() {
		return roleGroupDao.findAllByName();
	}

	public RoleGroup find( int id ) {
		return roleGroupDao.find( id );
	}

	public void save( RoleGroup role ) {
		roleGroupDao.save( role );
	}

	public void update( RoleGroup roleGroup ) {
		roleGroupDao.update( roleGroup );
	}

	public ResponseEntity validate( RoleGroup roleGroup ) {
		// Name can't be null
		if ( roleGroup.getName() == null || roleGroup.getName().equals( "" ) )
			return new ResponseEntity( "You must provide a name", HttpStatus.BAD_REQUEST );

		// Position can't be null
		if ( roleGroup.getPosition() == null )
			return new ResponseEntity( "You must provide a position", HttpStatus.BAD_REQUEST );

		// Name must be unique
		RoleGroup roleGroup2 = roleGroupDao.findByName( roleGroup.getName() );
		if ( roleGroup2 != null && roleGroup2.getId() != roleGroup2.getId() )
			return new ResponseEntity( "Name must be unique", HttpStatus.BAD_REQUEST );

		// Position must be unique
		roleGroup2 = roleGroupDao.findByPosition( roleGroup.getPosition() );
		if ( roleGroup2 != null && roleGroup.getId() != roleGroup2.getId() )
			return new ResponseEntity( "Position must be unique", HttpStatus.BAD_REQUEST );
		return null;
	}
}
