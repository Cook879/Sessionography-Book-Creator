package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.Source;
import uk.me.richardcook.sessionography.model.SourceJoined;

import java.util.List;


public interface SourceDao {

	List<Source> findAll();

	Source find( int id );

	void save( Source source );

	void update( Source source );

	List<Source> search( String query );

	SourceJoined findJoined( int id );
}
