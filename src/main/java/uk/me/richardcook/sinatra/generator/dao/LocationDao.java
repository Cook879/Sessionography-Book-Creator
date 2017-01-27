package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.Location;

import java.util.List;


public interface LocationDao {

	List<Location> findAll();

	Location find( int id );

	void save( Location location );

	void update( Location location );

	Location findByName( String name );

	List<Location> search( String query );
}
