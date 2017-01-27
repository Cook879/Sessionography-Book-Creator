package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.SectionDao;
import uk.me.richardcook.sinatra.generator.model.Section;

import java.util.List;


@Service
public class SectionService {

	@Autowired
	private SectionDao sectionDao;

	public List<Section> findAll() {
		return sectionDao.findAll();
	}

	public Section find( int id ) {
		return sectionDao.find( id );
	}

	public void save( Section section ) {
		sectionDao.save( section );
	}

	public void update( Section section ) {
		sectionDao.update( section );
	}

	public ResponseEntity validate( Section section ) {
		if ( section.getTitle() == null || section.getTitle().equals( "" ) )
			return new ResponseEntity( "You must provide a title", HttpStatus.BAD_REQUEST );

		if ( section.getDescription() == null || section.getDescription().equals( "" ) )
			return new ResponseEntity( "You must provide a description", HttpStatus.BAD_REQUEST );

		if ( section.getPosition() == null )
			return new ResponseEntity( "You must provide a position", HttpStatus.BAD_REQUEST );

		// Position must be unique
		Section section1 = sectionDao.findByPosition( section.getPosition() );
		if ( section1 != null && section.getId() != section1.getId() )
			return new ResponseEntity( "Position must be unique", HttpStatus.BAD_REQUEST );

		return null;
	}
}
