package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.BookString;

import java.util.List;

public interface BookStringDao {

	List<BookString> findAll();

	BookString find( int id );

	void save( BookString with );

	void update( BookString with );

	BookString findByName( String name );
}
