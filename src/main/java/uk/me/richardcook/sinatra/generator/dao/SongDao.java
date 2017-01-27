package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.Song;
import uk.me.richardcook.sinatra.generator.model.SongJoined;
import uk.me.richardcook.sinatra.generator.model.SongJoinedBook;

import java.util.List;

public interface SongDao {
	List<Song> findAll();

	List<SongJoined> findAllJoined();

	Song find( int id );

	void save( Song song );

	void update( Song song );

	Song findByTitle( String title );

	List<Song> search( String query );

	SongJoined findJoined( int id );

	List<SongJoinedBook> findAllJoinedBook();
}
