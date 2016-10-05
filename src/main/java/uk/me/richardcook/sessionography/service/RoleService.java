package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.RoleDao;
import uk.me.richardcook.sessionography.model.Role;

import java.util.List;


@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private RoleGroupService roleGroupService;

	public List<Role> findAll() {
		return roleDao.findAll();
	}

	public Role find( int id ) {
		return roleDao.find( id );
	}

	public void save( Role role ) {
		roleDao.save( role );
	}

	public void update( Role role ) {
		roleDao.update( role );
	}

	public ResponseEntity validate( Role role ) {
		// Name can't be null
		if ( role.getName() == null || role.getName().equals( "" ) )
			return new ResponseEntity( "You must provide a name", HttpStatus.BAD_REQUEST );

		// Abbreviation can't be null
		if ( role.getAbbreviation() == null || role.getAbbreviation().equals( "" ) )
			return new ResponseEntity( "You must provide an abbreviation", HttpStatus.BAD_REQUEST );

		// RoleView group can't be null
		if ( role.getGroup() == null )
			return new ResponseEntity( "You must provide a role group", HttpStatus.BAD_REQUEST );

		// Position can't be null
		if ( role.getPosition() == null )
			return new ResponseEntity( "You must provide a role group", HttpStatus.BAD_REQUEST );

		// Name must be unique
		Role role2 = roleDao.findByName( role.getName() );
		if ( role2 != null && role2.getId() != role.getId() )
			return new ResponseEntity( "Name must be unique", HttpStatus.BAD_REQUEST );

		// Abbreviation must be unique
		role2 = roleDao.findByAbbreviation( role.getAbbreviation() );
		if ( role2 != null && role2.getId() != role.getId() )
			return new ResponseEntity( "Abbreviation must be unique", HttpStatus.BAD_REQUEST );

		// Position and group combo must be unique
		role2 = roleDao.findByPosition( role.getGroup(), role.getPosition() );
		if ( role2 != null && role2.getId() != role.getId() )
			return new ResponseEntity( "The combination of position and role group must be unique", HttpStatus.BAD_REQUEST );

		// Group must be a legit group id
		if ( roleGroupService.find( role.getGroup() ) == null )
			return new ResponseEntity( "The role group you provided is not valid", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<Role> search( String query ) {
		return roleDao.search( query );
	}
}
