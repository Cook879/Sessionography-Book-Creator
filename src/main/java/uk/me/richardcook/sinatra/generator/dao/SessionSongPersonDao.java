package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.SessionSongPerson;
import uk.me.richardcook.sinatra.generator.model.SessionSongPersonJoined;

import java.util.List;


public interface SessionSongPersonDao {

	List<SessionSongPerson> findAll();

	SessionSongPerson find( int id );

	void save( SessionSongPerson sessionSongPerson );

	void update( SessionSongPerson sessionSongPerson );

	SessionSongPerson findBySessionSongPersonRole( Integer sessionSong, Integer person, Integer role );

	List<SessionSongPersonJoined> findForSession( List<Integer> ids );

	void deleteForSession( List<Integer> ids );
}
