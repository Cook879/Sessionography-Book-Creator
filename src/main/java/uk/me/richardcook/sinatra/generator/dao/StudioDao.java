package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.Studio;

import java.util.List;


public interface StudioDao {

	List<Studio> findAll();

	Studio find( int id );

	void save( Studio studio );

	void update( Studio studio );

	Studio findByName( String name );

	List<Studio> search( String query );
}
