package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.SourceDao;
import uk.me.richardcook.sessionography.model.Source;
import uk.me.richardcook.sessionography.model.SourceJoined;

import java.util.List;


@Service
public class SourceService {

	@Autowired
	private SourceDao sourceDao;

	@Autowired
	private StudioService studioService;

	@Autowired
	private SourceTypeService sourceTypeService;

	public List<Source> findAll() {
		return sourceDao.findAll();
	}

	public Source find( int id ) {
		return sourceDao.find( id );
	}

	public SourceJoined findJoined( int id ) {
		return sourceDao.findJoined( id );
	}

	public void save( Source source ) {
		sourceDao.save( source );
	}

	public void update( Source source ) {
		sourceDao.update( source );
	}

	public ResponseEntity validate( Source source ) {
		// Name can't be null
		if ( source.getName() == null || source.getName().equals( "" ) )
			return new ResponseEntity( "You must provide a name", HttpStatus.BAD_REQUEST );
		// type can't be null
		if ( source.getType() == null )
			return new ResponseEntity( "You must provide a type", HttpStatus.BAD_REQUEST );
		// year can't be null
		if ( source.getYear() == null || source.getYear().equals( "" ) )
			return new ResponseEntity( "You must provide a year", HttpStatus.BAD_REQUEST );

		// Studio must be a legit studio id
		if ( source.getStudio() != null && studioService.find( source.getStudio() ) == null )
			return new ResponseEntity( "The studio you provided is not valid", HttpStatus.BAD_REQUEST );
		// type must be a legit source type id
		if ( source.getType() != null && sourceTypeService.find( source.getType() ) == null )
			return new ResponseEntity( "The source type you provided is not valid", HttpStatus.BAD_REQUEST );

		return null;
	}

	public List<Source> search( String query ) {
		return sourceDao.search( query );
	}
}
