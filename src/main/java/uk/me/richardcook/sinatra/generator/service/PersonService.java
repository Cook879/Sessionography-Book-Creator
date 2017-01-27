package uk.me.richardcook.sinatra.generator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sinatra.generator.dao.PersonDao;
import uk.me.richardcook.sinatra.generator.model.Person;

import java.util.List;


@Service
public class PersonService {

	@Autowired
	private PersonDao personDao;

	public List<Person> findAll() {
		return personDao.findAll();
	}

	public Person find( int id ) {
		return personDao.find( id );
	}

	public void save( Person person ) {
		personDao.save( person );
	}

	public void update( Person person ) {
		personDao.update( person );
	}

	public ResponseEntity validate( Person person ) {
		// First name can be null, so let's convert empty string to null
		if ( person.getFirstName().equals( "" ) )
			person.setFirstName( null );

		// But last name can't be null
		if ( person.getLastName() == null || person.getLastName() == "" )
			return new ResponseEntity( "You must provide a last name", HttpStatus.BAD_REQUEST );

		// Check no one else has this name combo
		Person person2 = personDao.findByName( person.getFirstName(), person.getLastName() );
		if ( person2 != null && person2.getId() != person.getId() )
			return new ResponseEntity( "First and last name combination must be unique", HttpStatus.BAD_REQUEST );

		return null;
	}

	public List<Person> search( String query ) {
		return personDao.search( query );
	}
}
