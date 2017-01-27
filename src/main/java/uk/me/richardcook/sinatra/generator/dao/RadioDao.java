package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.Radio;
import uk.me.richardcook.sinatra.generator.model.RadioJoined;

import java.util.List;


public interface RadioDao {

	List<Radio> findAll();

	Radio find( int id );

	void save( Radio radio );

	void update( Radio radio );

	List<Radio> search( String query );

	RadioJoined findJoined( int id );
}
