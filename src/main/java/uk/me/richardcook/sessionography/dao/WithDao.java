package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.With;

import java.util.List;


public interface WithDao {

	List<With> findAll();

	With find( int id );

	void save( With with );

	void update( With with );

	With findByName( String name );

	List<With> search( String query );
}
