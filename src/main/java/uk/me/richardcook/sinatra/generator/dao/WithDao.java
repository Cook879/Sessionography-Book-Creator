package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.With;

import java.util.List;


public interface WithDao {

	List<With> findAll();

	With find( int id );

	void save( With with );

	void update( With with );

	With findByName( String name );

	List<With> search( String query );
}
