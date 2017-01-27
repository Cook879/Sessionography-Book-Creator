package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.Session;
import uk.me.richardcook.sinatra.generator.model.SessionJoined;
import uk.me.richardcook.sinatra.generator.model.SessionView;

import java.util.List;

public interface SessionDao {

	List<SessionView> findForSection( int id );

	List<SessionView> findAllView();

	List<Session> findAll();

	Session find( int id );

	void save( Session session );

	void update( Session session );

	SessionJoined findJoined( int id );

	List<Session> search( String query );
}
