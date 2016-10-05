package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.Format;

import java.util.List;

public interface FormatDao {

	List<Format> findAll();

	Format find( int id );

	void save( Format format );

	void update( Format format );

	Format findByName( String name );
}
