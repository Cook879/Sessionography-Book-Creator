package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.Label;

import java.util.List;

public interface LabelDao {

	List<Label> findAll();

	Label find( int id );

	void save( Label label );

	void update( Label label );

	Label findByName( String name );

	List<Label> search( String query );
}
