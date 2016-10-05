package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.WithDao;
import uk.me.richardcook.sessionography.model.With;

import java.util.List;


@Service
public class WithService {

	@Autowired
	private WithDao withDao;

	public List<With> findAll() {
		return withDao.findAll();
	}

	public With find( int id ) {
		return withDao.find( id );
	}

	public void save( With with ) {
		withDao.save( with );
	}

	public void update( With with ) {
		withDao.update( with );
	}

	public ResponseEntity validate( With with ) {
		// Name can't be null
		if ( with.getName() == null || with.getName().equals( "" ) )
			return new ResponseEntity( "You must provide a name", HttpStatus.BAD_REQUEST );

		// Name must be unique
		With with1 = withDao.findByName( with.getName() );
		if ( with1 != null && with.getId() != with1.getId() )
			return new ResponseEntity( "Name must be unique", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<With> search( String query ) {
		return withDao.search( query );
	}
}
