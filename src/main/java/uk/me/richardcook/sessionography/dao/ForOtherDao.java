package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.ForOther;

import java.util.List;

public interface ForOtherDao {

	List<ForOther> findAll();

	ForOther find( int id );

	void save( ForOther forOther );

	void update( ForOther forOther );

	List<ForOther> search( String query );
}
