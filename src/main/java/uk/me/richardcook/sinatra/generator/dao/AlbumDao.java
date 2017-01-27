package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.Album;
import uk.me.richardcook.sinatra.generator.model.AlbumJoined;

import java.util.List;

public interface AlbumDao {

	List<Album> findAll();

	Album find( int id );

	void save( Album album );

	void update( Album album );

	List<Album> search( String query );

	AlbumJoined findJoined( int id );
}
