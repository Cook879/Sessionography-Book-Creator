package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.Release;
import uk.me.richardcook.sessionography.model.ReleaseJoined;

import java.util.List;


public interface ReleaseDao {

	List<Release> findAll();

	Release find( int id );

	void save( Release person );

	void update( Release person );

	List<Release> search( String query );

	ReleaseJoined findJoined( int id );
}
