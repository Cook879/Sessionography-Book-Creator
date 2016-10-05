package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.SessionDao;
import uk.me.richardcook.sessionography.model.Session;
import uk.me.richardcook.sessionography.model.SessionJoined;

import java.util.List;


@Service
public class SessionService {

	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private SectionService sectionService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private LabelService labelService;

	@Autowired
	private AlbumService albumService;

	@Autowired
	private ForOtherService forOtherService;

	@Autowired
	private SessionTypeService sessionTypeService;

	@Autowired
	private RadioService radioService;

	@Autowired
	private TelevisionService televisionService;

	@Autowired
	private FilmService filmService;

	@Autowired
	private WithService withService;

	public List<Session> findAll() {
		return sessionDao.findAll();
	}

	public Session find( int id ) {
		return sessionDao.find( id );
	}

	public void save( Session session ) {
		sessionDao.save( session );
	}

	public void update( Session session ) {
		sessionDao.update( session );
	}

	public ResponseEntity validate( Session session ) {
		// The following fields can be null, and so we need to convert empty string to null
		if ( session.getNotes().equals( "" ) )
			session.setNotes( null );
		if ( session.getSessionNumber().equals( "" ) )
			session.setSessionNumber( null );

		// Date can't be null
		// TODO empty date?
		if ( session.getDate() == null )
			return new ResponseEntity( "You must provide a date", HttpStatus.BAD_REQUEST );

		// Date display can't be null
		if ( session.getDateDisplay() == null )
			return new ResponseEntity( "You must provide a display date", HttpStatus.BAD_REQUEST );

		// Section can't be null
		if ( session.getSection() == null )
			return new ResponseEntity( "You must provide a section", HttpStatus.BAD_REQUEST );

		// Location can't be null
		if ( session.getLocation() == null )
			return new ResponseEntity( "You must provide a location", HttpStatus.BAD_REQUEST );
		// Type can't be null
		if ( session.getType() == null )
			return new ResponseEntity( "You must provide a session type", HttpStatus.BAD_REQUEST );
		// With can't be null
		if ( session.getWith() == null )
			return new ResponseEntity( "You must provide a with", HttpStatus.BAD_REQUEST );

		// Section must be a legit section id
		if ( sectionService.find( session.getSection() ) == null )
			return new ResponseEntity( "The section you provided is not valid", HttpStatus.BAD_REQUEST );
		// Location must be a legit location id
		if ( locationService.find( session.getLocation() ) == null )
			return new ResponseEntity( "The location you provided is not valid", HttpStatus.BAD_REQUEST );
		// Label must be a legit label id
		if ( session.getLabel() != null && labelService.find( session.getLabel() ) == null )
			return new ResponseEntity( "The label you provided is not valid", HttpStatus.BAD_REQUEST );
		// AlbumView must be a legit album id
		if ( session.getForAlbum() != null && albumService.find( session.getForAlbum() ) == null )
			return new ResponseEntity( "The album you provided is not valid", HttpStatus.BAD_REQUEST );
		// For Other must be a legit for other id
		if ( session.getForOther() != null && forOtherService.find( session.getForOther() ) == null )
			return new ResponseEntity( "The other you provided is not valid", HttpStatus.BAD_REQUEST );
		// Type must be a legit type id
		if ( sessionTypeService.find( session.getType() ) == null )
			return new ResponseEntity( "The session type you provided is not valid", HttpStatus.BAD_REQUEST );
		// RadioView must be a legit radio episode id
		if ( session.getRadio() != null && radioService.find( session.getRadio() ) == null )
			return new ResponseEntity( "The radio episode you provided is not valid", HttpStatus.BAD_REQUEST );
		// TelevisionView must be a legit television episode id
		if ( session.getTelevision() != null && televisionService.find( session.getTelevision() ) == null )
			return new ResponseEntity( "The television episode you provided is not valid", HttpStatus.BAD_REQUEST );
		// FilmView must be a legit film id
		if ( session.getFilm() != null && filmService.find( session.getFilm() ) == null )
			return new ResponseEntity( "The film you provided is not valid", HttpStatus.BAD_REQUEST );
		// With must be a legit with id
		if ( withService.find( session.getWith() ) == null )
			return new ResponseEntity( "The with you provided is not valid", HttpStatus.BAD_REQUEST );
		return null;
	}

	public SessionJoined findJoined( int id ) {
		return sessionDao.findJoined( id );
	}


	public List<Session> search( String query ) {
		return sessionDao.search( query );
	}
}
