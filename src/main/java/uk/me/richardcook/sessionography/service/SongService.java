package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.SongDao;
import uk.me.richardcook.sessionography.model.Song;
import uk.me.richardcook.sessionography.model.SongJoined;

import java.util.List;


@Service
public class SongService {

	@Autowired
	private SongDao songDao;

	@Autowired
	private SourceService sourceService;

	public List<Song> findAll() {
		return songDao.findAll();
	}

	public Song find( int id ) {
		return songDao.find( id );
	}

	public void save( Song song ) {
		songDao.save( song );
	}

	public void update( Song song ) {
		songDao.update( song );
	}

	public ResponseEntity validate( Song song ) {
		// The following fields can be null, and so we need to convert empty string to null
		if ( song.getNotes() != null && song.getNotes().equals( "" ) )
			song.setNotes( null );

		// Title can't be null
		if ( song.getTitle() == null || song.getTitle().equals( "" ) )
			return new ResponseEntity( "You must provide a title", HttpStatus.BAD_REQUEST );
		// Year can't be null
		if ( song.getYear() == null || song.getYear().equals( "" ) )
			return new ResponseEntity( "You must provide a year", HttpStatus.BAD_REQUEST );
		// Short title can't be null
		if ( song.getShortTitle() == null || song.getShortTitle().equals( "" ) )
			return new ResponseEntity( "You must provide a short title", HttpStatus.BAD_REQUEST );

		// SourceView must be a legit source id
		if ( song.getSource() != null && sourceService.find( song.getSource() ) == null )
			return new ResponseEntity( "The source you provided is not valid", HttpStatus.BAD_REQUEST );
		// Source2 must be a legit source id
		if ( song.getSource2() != null && sourceService.find( song.getSource2() ) == null )
			return new ResponseEntity( "The second source you provided is not valid", HttpStatus.BAD_REQUEST );

		// Check no other song has this title
		Song song1 = songDao.findByTitle( song.getTitle() );
		if ( song1 != null && song.getId() != song1.getId() )
			return new ResponseEntity( "Title must be unique", HttpStatus.BAD_REQUEST );

		return null;
	}

	public List<Song> search( String query ) {
		return songDao.search( query );
	}

	public SongJoined findJoined( int id ) {
		return songDao.findJoined( id );
	}
}
