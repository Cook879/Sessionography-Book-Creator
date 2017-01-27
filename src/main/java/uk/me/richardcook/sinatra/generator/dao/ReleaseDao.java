package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.Release;
import uk.me.richardcook.sinatra.generator.model.ReleaseJoined;

import java.util.List;


public interface ReleaseDao {

	List<Release> findAll();

	Release find( int id );

	void save( Release person );

	void update( Release person );

	List<Release> search( String query );

	ReleaseJoined findJoined( int id );
}
