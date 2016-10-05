package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.Album;
import uk.me.richardcook.sessionography.model.AlbumJoined;

import java.util.List;

public interface AlbumDao {

	List<Album> findAll();

	Album find( int id );

	void save( Album album );

	void update( Album album );

	List<Album> search( String query );

	AlbumJoined findJoined( int id );
}
