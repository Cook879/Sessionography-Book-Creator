package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.Television;
import uk.me.richardcook.sessionography.model.TelevisionJoined;

import java.util.List;


public interface TelevisionDao {

	List<Television> findAll();

	Television find( int id );

	void save( Television television );

	void update( Television television );

	List<Television> search( String query );

	TelevisionJoined findJoined( int id );
}
