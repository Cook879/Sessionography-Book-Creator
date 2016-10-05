package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.LocationDao;
import uk.me.richardcook.sessionography.model.Location;

import java.util.List;


@Service
public class LocationService {

	@Autowired
	private LocationDao locationDao;

	public List<Location> findAll() {
		return locationDao.findAll();
	}

	public Location find( int id ) {
		return locationDao.find( id );
	}

	public void save( Location location ) {
		locationDao.save( location );
	}

	public void update( Location location ) {
		locationDao.update( location );
	}

	public ResponseEntity validate( Location location ) {
		// Name can't be null
		if ( location.getName() == null || location.getName().equals( "" ) )
			return new ResponseEntity( "You must provide a name", HttpStatus.BAD_REQUEST );

		// Name must be unique
		Location location1 = locationDao.findByName( location.getName() );
		if ( location1 != null && location.getId() != location1.getId() )
			return new ResponseEntity( "Name must be unique", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<Location> search( String query ) {
		return locationDao.search( query );
	}
}
