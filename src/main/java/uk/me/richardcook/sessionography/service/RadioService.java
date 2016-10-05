package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.RadioDao;
import uk.me.richardcook.sessionography.model.Radio;
import uk.me.richardcook.sessionography.model.RadioJoined;

import java.util.List;


@Service
public class RadioService {

	@Autowired
	private RadioDao radioDao;

	@Autowired
	private StudioService studioService;

	public List<Radio> findAll() {
		return radioDao.findAll();
	}

	public Radio find( int id ) {
		return radioDao.find( id );
	}

	public void save( Radio radio ) {
		radioDao.save( radio );
	}

	public void update( Radio radio ) {
		radioDao.update( radio );
	}

	public ResponseEntity validate( Radio radio ) {
		// Title can't be null
		if ( radio.getTitle() == null || radio.getTitle().equals( "" ) )
			return new ResponseEntity( "You must provide a title", HttpStatus.BAD_REQUEST );

		// Studio can't be null
		if ( radio.getNetwork() == null )
			return new ResponseEntity( "You must provide a network", HttpStatus.BAD_REQUEST );

		// Year can't be null
		if ( radio.getYear() == null || radio.getYear().equals( "" ) )
			return new ResponseEntity( "You must provide a date", HttpStatus.BAD_REQUEST );

		// Label must be a legit label id
		if ( studioService.find( radio.getNetwork() ) == null )
			return new ResponseEntity( "The network you provided is not valid", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<Radio> search( String query ) {
		return radioDao.search( query );
	}

	public RadioJoined findJoined( int id ) {
		return radioDao.findJoined( id );
	}
}
