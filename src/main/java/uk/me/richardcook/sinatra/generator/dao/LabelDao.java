package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.Label;

import java.util.List;

public interface LabelDao {

	List<Label> findAll();

	Label find( int id );

	void save( Label label );

	void update( Label label );

	Label findByName( String name );

	List<Label> search( String query );
}
