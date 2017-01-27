package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.BookStringDao;
import uk.me.richardcook.sinatra.generator.model.BookString;

import java.util.List;


@Service
public class BookStringService {

	@Autowired
	private BookStringDao bookStringDao;

	public List<BookString> findAll() {
		return bookStringDao.findAll();
	}

	public BookString find( int id ) {
		return bookStringDao.find( id );
	}

	public void save( BookString bookString ) {
		bookStringDao.save( bookString );
	}

	public void update( BookString bookString ) {
		bookStringDao.update( bookString );
	}

	public ResponseEntity validate( BookString bookString ) {
		// Name can't be null
		if ( bookString.getName() == null || bookString.getName().equals( "" ) )
			return new ResponseEntity( "You must provide a name", HttpStatus.BAD_REQUEST );

		// Value can't be null
		if ( bookString.getValue() == null || bookString.getValue().equals( "" ) )
			return new ResponseEntity( "You must provide a value", HttpStatus.BAD_REQUEST );

		// Name must be unique
		BookString bookString1 = bookStringDao.findByName( bookString.getName() );
		if ( bookString1 != null && bookString.getId() != bookString1.getId() )
			return new ResponseEntity( "Name must be unique", HttpStatus.BAD_REQUEST );
		return null;
	}
}
