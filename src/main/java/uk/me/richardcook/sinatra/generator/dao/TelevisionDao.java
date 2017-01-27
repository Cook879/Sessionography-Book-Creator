package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.Television;
import uk.me.richardcook.sinatra.generator.model.TelevisionJoined;

import java.util.List;


public interface TelevisionDao {

	List<Television> findAll();

	Television find( int id );

	void save( Television television );

	void update( Television television );

	List<Television> search( String query );

	TelevisionJoined findJoined( int id );
}
