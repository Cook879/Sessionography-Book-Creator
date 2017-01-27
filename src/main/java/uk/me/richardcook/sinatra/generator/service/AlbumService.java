package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.AlbumDao;
import uk.me.richardcook.sinatra.generator.model.Album;
import uk.me.richardcook.sinatra.generator.model.AlbumJoined;

import java.util.List;

@Service
public class AlbumService {

	@Autowired
	private AlbumDao albumDao;

	@Autowired
	private LabelService labelService;

	public List<Album> findAll() {
		return albumDao.findAll();
	}

	public Album find( int id ) {
		return albumDao.find( id );
	}

	public void save( Album album ) {
		albumDao.save( album );
	}

	public void update( Album album ) {
		albumDao.update( album );
	}

	public ResponseEntity validate( Album album ) {
		// Title can't be null
		if ( album.getTitle() == null || album.getTitle().equals( "" ) )
			return new ResponseEntity( "You must provide a title", HttpStatus.BAD_REQUEST );

		// Label can't be null
		if ( album.getLabel() == null )
			return new ResponseEntity( "You must provide a label", HttpStatus.BAD_REQUEST );

		// Year can't be null
		if ( album.getYear() == null || album.getYear().equals( "" ) )
			return new ResponseEntity( "You must provide a year", HttpStatus.BAD_REQUEST );

		// Label must be a legit label id
		if ( labelService.find( album.getLabel() ) == null )
			return new ResponseEntity( "The label you provided is not valid", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<Album> search( String query ) {
		return albumDao.search( query );
	}

	public AlbumJoined findJoined( int id ) {
		return albumDao.findJoined( id );
	}
}
