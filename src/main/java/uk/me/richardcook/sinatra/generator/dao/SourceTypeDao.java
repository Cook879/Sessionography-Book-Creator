package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.SourceType;

import java.util.List;


public interface SourceTypeDao {

	List<SourceType> findAll();

	SourceType find( int id );

	void save( SourceType sourceType );

	void update( SourceType sourceType );

	SourceType findByName( String name );
}
