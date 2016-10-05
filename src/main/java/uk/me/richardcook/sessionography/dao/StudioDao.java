package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.Studio;

import java.util.List;


public interface StudioDao {

	List<Studio> findAll();

	Studio find( int id );

	void save( Studio studio );

	void update( Studio studio );

	Studio findByName( String name );

	List<Studio> search( String query );
}
