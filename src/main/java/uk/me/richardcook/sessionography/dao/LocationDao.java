package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.Location;

import java.util.List;


public interface LocationDao {

	List<Location> findAll();

	Location find( int id );

	void save( Location location );

	void update( Location location );

	Location findByName( String name );

	List<Location> search( String query );
}
