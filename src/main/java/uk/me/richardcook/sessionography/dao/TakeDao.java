package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.Take;
import uk.me.richardcook.sessionography.model.TakeJoined;

import java.util.List;


public interface TakeDao {

	List<Take> findAll();

	Take find( int id );

	void save( Take take );

	void update( Take take );

	Take findBySessionSongPosition( Integer sessionSong, Integer position );

	List<TakeJoined> findForSessionSong( int id );

	TakeJoined findJoined( int id );

	void delete( int id );
}
