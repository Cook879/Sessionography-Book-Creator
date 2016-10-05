package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.StudioDao;
import uk.me.richardcook.sessionography.model.Studio;

import java.util.List;


@Service
public class StudioService {

	@Autowired
	private StudioDao studioDao;

	public List<Studio> findAll() {
		return studioDao.findAll();
	}

	public Studio find( int id ) {
		return studioDao.find( id );
	}

	public void save( Studio studio ) {
		studioDao.save( studio );
	}

	public void update( Studio studio ) {
		studioDao.update( studio );
	}

	public ResponseEntity validate( Studio studio ) {
		// Name can't be null
		if ( studio.getName() == null || studio.getName().equals( "" ) )
			return new ResponseEntity( "You must provide a name", HttpStatus.BAD_REQUEST );

		// Name must be unique
		Studio studio1 = studioDao.findByName( studio.getName() );
		if ( studio1 != null && studio.getId() != studio1.getId() )
			return new ResponseEntity( "Name must be unique", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<Studio> search( String query ) {
		return studioDao.search( query );
	}
}
