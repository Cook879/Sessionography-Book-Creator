package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.SessionType;

import java.util.List;

public interface SessionTypeDao {

	List<SessionType> findAll();

	SessionType find( int id );

	void save( SessionType sessionType );

	void update( SessionType sessionType );

	SessionType findByName( String name );
}
