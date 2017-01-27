package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.SessionSongSong;
import uk.me.richardcook.sinatra.generator.model.SessionSongSongJoined;

import java.util.List;

public interface SessionSongSongDao {

	List<SessionSongSong> findAll();

	SessionSongSong find( int id );

	void save( SessionSongSong person );

	void update( SessionSongSong person );

	SessionSongSong findBySessionSongPosition( Integer sessionSong, Integer position );

	List<SessionSongSongJoined> findForSessionSong( int id );

	void deleteForSessionSong( int id );
}
