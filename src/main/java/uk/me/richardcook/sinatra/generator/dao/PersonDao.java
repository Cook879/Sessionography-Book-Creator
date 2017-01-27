package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.Person;

import java.util.List;

public interface PersonDao {
	List<Person> findAll();

	Person find( int id );

	void save( Person person );

	void update( Person person );

	Person findByName( String firstName, String lastName );

	List<Person> search( String query );
}
