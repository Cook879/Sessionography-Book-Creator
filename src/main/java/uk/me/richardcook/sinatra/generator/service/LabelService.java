package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.LabelDao;
import uk.me.richardcook.sinatra.generator.model.Label;

import java.util.List;


@Service
public class LabelService {

	@Autowired
	private LabelDao labelDao;

	public List<Label> findAll() {
		return labelDao.findAll();
	}

	public Label find( int id ) {
		return labelDao.find( id );
	}

	public void save( Label label ) {
		labelDao.save( label );
	}

	public void update( Label label ) {
		labelDao.update( label );
	}

	public ResponseEntity validate( Label label ) {
		// Name can't be null
		if ( label.getName() == null || label.getName().equals( "" ) )
			return new ResponseEntity( "You must provide a name", HttpStatus.BAD_REQUEST );

		// Name must be unique
		Label label1 = labelDao.findByName( label.getName() );
		if ( label1 != null && label.getId() != label1.getId() )
			return new ResponseEntity( "Name must be unique", HttpStatus.BAD_REQUEST );
		return null;
	}

	public List<Label> search( String query ) {
		return labelDao.search( query );
	}
}
