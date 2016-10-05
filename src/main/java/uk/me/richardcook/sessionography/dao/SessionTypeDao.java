package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.SessionType;

import java.util.List;

public interface SessionTypeDao {

	List<SessionType> findAll();

	SessionType find( int id );

	void save( SessionType sessionType );

	void update( SessionType sessionType );

	SessionType findByName( String name );
}
