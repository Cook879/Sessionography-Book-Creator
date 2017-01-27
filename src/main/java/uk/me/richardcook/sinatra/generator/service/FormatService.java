package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.FormatDao;
import uk.me.richardcook.sinatra.generator.model.Format;

import java.util.List;


@Service
public class FormatService {

	@Autowired
	private FormatDao formatDao;

	public List<Format> findAll() {
		return formatDao.findAll();
	}

	public Format find( int id ) {
		return formatDao.find( id );
	}

	public void save( Format format ) {
		formatDao.save( format );
	}

	public void update( Format format ) {
		formatDao.update( format );
	}

	public ResponseEntity validate( Format format ) {
		// Name can't be null
		if ( format.getName() == null || format.getName().equals( "" ) )
			return new ResponseEntity( "You must provide a name", HttpStatus.BAD_REQUEST );

		// Name must be unique
		Format format1 = formatDao.findByName( format.getName() );
		if ( format1 != null && format.getId() != format1.getId() )
			return new ResponseEntity( "Name must be unique", HttpStatus.BAD_REQUEST );
		return null;
	}

}
