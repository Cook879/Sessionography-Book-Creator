package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.ForOther;

import java.util.List;

public interface ForOtherDao {

	List<ForOther> findAll();

	ForOther find( int id );

	void save( ForOther forOther );

	void update( ForOther forOther );

	List<ForOther> search( String query );
}
