package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.Session;
import uk.me.richardcook.sessionography.model.SessionJoined;
import uk.me.richardcook.sessionography.model.SessionView;

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
