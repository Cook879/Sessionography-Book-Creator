package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.Format;

import java.util.List;

public interface FormatDao {

	List<Format> findAll();

	Format find( int id );

	void save( Format format );

	void update( Format format );

	Format findByName( String name );
}
