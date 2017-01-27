package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.TelevisionDao;
import uk.me.richardcook.sinatra.generator.model.Television;
import uk.me.richardcook.sinatra.generator.model.TelevisionJoined;

import java.util.List;


@Service
public class TelevisionService {

	@Autowired
	private TelevisionDao televisionDao;

	@Autowired
	private StudioService studioService;

	public List<Television> findAll() {
		return televisionDao.findAll();
	}

	public Television find( int id ) {
		return televisionDao.find( id );
	}

	public void save( Television television ) {
		televisionDao.save( television );
	}

	public void update( Television television ) {
		televisionDao.update( television );
	}

	public ResponseEntity validate( Television television ) {
		// Title can't be null
		if ( television.getTitle() == null || television.getTitle().equals( "" ) )
			return new ResponseEntity( "You must provide a title", HttpStatus.BAD_REQUEST );

		// Studio can't be null
		if ( television.getNetwork() == null )
			return new ResponseEntity( "You must provide a network", HttpStatus.BAD_REQUEST );

		// Year can't be null
		if ( television.getYear() == null || television.getYear().equals( "" ) )
			return new ResponseEntity( "You must provide a date", HttpStatus.BAD_REQUEST );

		// Label must be a legit label id
		if ( studioService.find( television.getNetwork() ) == null )
			return new ResponseEntity( "The network you provided is not valid", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<Television> search( String query ) {
		return televisionDao.search( query );
	}

	public TelevisionJoined findJoined( int id ) {
		return televisionDao.findJoined( id );
	}
}
