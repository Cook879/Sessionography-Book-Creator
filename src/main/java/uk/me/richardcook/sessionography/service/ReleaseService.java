package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.ReleaseDao;
import uk.me.richardcook.sessionography.model.Release;
import uk.me.richardcook.sessionography.model.ReleaseJoined;

import java.util.List;


@Service
public class ReleaseService {

	@Autowired
	private ReleaseDao releaseDao;

	@Autowired
	private LabelService labelService;

	@Autowired
	private FormatService formatService;

	public List<Release> findAll() {
		return releaseDao.findAll();
	}

	public Release find( int id ) {
		return releaseDao.find( id );
	}

	public void save( Release release ) {
		releaseDao.save( release );
	}

	public void update( Release release ) {
		releaseDao.update( release );
	}

	public ResponseEntity validate( Release release ) {
		// The following fields can be null, and so we need to convert empty string to null
		if ( release.getYear().equals( "" ) )
			release.setYear( null );

		// Title can't be null
		if ( release.getTitle() == null || release.getTitle().equals( "" ) )
			return new ResponseEntity( "You must provide a title", HttpStatus.BAD_REQUEST );

		// Label must be a legit label id
		if ( release.getLabel() != null && labelService.find( release.getLabel() ) == null )
			return new ResponseEntity( "The label you provided is not valid", HttpStatus.BAD_REQUEST );
		// Format must be a legit format id
		if ( release.getFormat() != null && formatService.find( release.getFormat() ) == null )
			return new ResponseEntity( "The format you provided is not valid", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<Release> search( String query ) {
		return releaseDao.search( query );
	}

	public ReleaseJoined findJoined( int id ) {
		return releaseDao.findJoined( id );
	}
}
