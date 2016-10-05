package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.SongPerson;
import uk.me.richardcook.sessionography.model.SongPersonJoined;

import java.util.List;


public interface SongPersonDao {

	List<SongPerson> findAll();

	SongPerson find( int id );

	void save( SongPerson songPerson );

	void update( SongPerson songPerson );

	SongPerson findBySongPersonRole( Integer song, Integer person, Integer role );

	List<SongPersonJoined> findForSong( int id );

	void deleteForSong( int id );
}
