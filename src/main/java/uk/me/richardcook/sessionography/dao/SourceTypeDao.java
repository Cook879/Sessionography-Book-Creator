package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.SourceType;

import java.util.List;


public interface SourceTypeDao {

	List<SourceType> findAll();

	SourceType find( int id );

	void save( SourceType sourceType );

	void update( SourceType sourceType );

	SourceType findByName( String name );
}
