package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.Section;

import java.util.List;

public interface SectionDao {
	List<Section> findAll();

	Section find( int id );

	void save( Section section );

	void update( Section section );

	Section findByPosition( Integer position );
}
