package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.TakeDao;
import uk.me.richardcook.sinatra.generator.model.Take;
import uk.me.richardcook.sinatra.generator.model.TakeJoined;

import java.util.List;


@Service
public class TakeService {

	@Autowired
	private TakeDao takeDao;

	@Autowired
	private SessionSongService sessionSongService;

	@Autowired
	private ReleaseService releaseService;

	@Autowired
	private FilmService filmService;

	@Autowired
	private RadioService radioService;

	@Autowired
	private TelevisionService televisionService;

	public List<Take> findAll() {
		return takeDao.findAll();
	}

	public Take find( int id ) {
		return takeDao.find( id );
	}

	public void save( Take take ) {
		takeDao.save( take );
	}

	public void update( Take take ) {
		takeDao.update( take );
	}

	public ResponseEntity validate( Take take ) {
		// The following fields can be null, and so we need to convert empty string to null
		if ( take.getNotes().equals( "" ) )
			take.setNotes( null );

		// SessionView song can't be null
		if ( take.getSessionSong() == null )
			return new ResponseEntity( "You must provide a session song", HttpStatus.BAD_REQUEST );
		// Title can't be null
		if ( take.getTitle() == null || take.getTitle().equals( "" ) )
			return new ResponseEntity( "You must provide a title", HttpStatus.BAD_REQUEST );
		// Length can't be null
		if ( take.getLength() == null || take.getLength().equals( "" ) )
			return new ResponseEntity( "You must provide a length", HttpStatus.BAD_REQUEST );
		// Original release can't be null
		if ( take.getOriginalRelease() == null )
			return new ResponseEntity( "You must provide an original release", HttpStatus.BAD_REQUEST );
		// Key release can't be null
		if ( take.getKeyRelease() == null )
			return new ResponseEntity( "You must provide a key release", HttpStatus.BAD_REQUEST );
		// Position can't be null
		if ( take.getPosition() == null || take.getPosition().equals( "" ) )
			return new ResponseEntity( "You must provide a position", HttpStatus.BAD_REQUEST );

		// SessionView song must be a legit source id
		if ( sessionSongService.find( take.getSessionSong() ) == null )
			return new ResponseEntity( "The source you provided is not valid", HttpStatus.BAD_REQUEST );
		// Original release must be a legit release id
		if ( releaseService.find( take.getOriginalRelease() ) == null )
			return new ResponseEntity( "The original release you provided is not valid", HttpStatus.BAD_REQUEST );
		// Key release must be a legit release id
		if ( releaseService.find( take.getKeyRelease() ) == null )
			return new ResponseEntity( "The key release you provided is not valid", HttpStatus.BAD_REQUEST );
		// FilmView must be a legit film id
		if ( take.getFilm() != null && filmService.find( take.getFilm() ) == null )
			return new ResponseEntity( "The film you provided is not valid", HttpStatus.BAD_REQUEST );
		// RadioView must be a legit radio id
		if ( take.getRadio() != null && radioService.find( take.getRadio() ) == null )
			return new ResponseEntity( "The radio you provided is not valid", HttpStatus.BAD_REQUEST );
		// TelevisionView must be a legit television id
		if ( take.getTelevision() != null && televisionService.find( take.getTelevision() ) == null )
			return new ResponseEntity( "The television you provided is not valid", HttpStatus.BAD_REQUEST );

		// Check no other take from this session song has this position
		Take take1 = takeDao.findBySessionSongPosition( take.getSessionSong(), take.getPosition() );
		if ( take1 != null && take.getId() != take1.getId() )
			return new ResponseEntity( "SessionView song and position combination must be unique", HttpStatus.BAD_REQUEST );

		return null;
	}

	public List<TakeJoined> findForSessionSong( int id ) {
		return takeDao.findForSessionSong( id );
	}

	public TakeJoined findJoined( int id ) {
		return takeDao.findJoined( id );
	}

	public void delete( int id ) {
		takeDao.delete( id );
	}
}
