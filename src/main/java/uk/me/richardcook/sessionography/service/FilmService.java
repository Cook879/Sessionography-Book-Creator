package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.FilmDao;
import uk.me.richardcook.sessionography.model.Film;
import uk.me.richardcook.sessionography.model.FilmJoined;

import java.util.List;


@Service
public class FilmService {

	@Autowired
	private FilmDao filmDao;

	@Autowired
	private StudioService studioService;

	public List<Film> findAll() {
		return filmDao.findAll();
	}

	public Film find( int id ) {
		return filmDao.find( id );
	}

	public void save( Film film ) {
		filmDao.save( film );
	}

	public void update( Film film ) {
		filmDao.update( film );
	}

	public ResponseEntity validate( Film film ) {
		// Title can't be null
		if ( film.getTitle() == null || film.getTitle().equals( "" ) )
			return new ResponseEntity( "You must provide a title", HttpStatus.BAD_REQUEST );

		// Studio can't be null
		if ( film.getStudio() == null )
			return new ResponseEntity( "You must provide a studio", HttpStatus.BAD_REQUEST );

		// Year can't be null
		if ( film.getYear() == null || film.getYear().equals( "" ) )
			return new ResponseEntity( "You must provide a year", HttpStatus.BAD_REQUEST );

		// Label must be a legit label id
		if ( studioService.find( film.getStudio() ) == null )
			return new ResponseEntity( "The studio you provided is not valid", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<Film> search( String query ) {
		return filmDao.search( query );
	}

	public FilmJoined findJoined( int id ) {
		return filmDao.findJoined( id );
	}
}
