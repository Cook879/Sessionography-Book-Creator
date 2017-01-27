package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.PersonRoleNote;

import java.util.Collection;
import java.util.List;

public interface PersonRoleNoteDao {

	List<PersonRoleNote> findCollection( Collection<Integer> ids );

	PersonRoleNote find( int id );

	List<PersonRoleNote> findForSession( int id );

	List<PersonRoleNote> findAll();

	void save( PersonRoleNote personRoleNote );

	void update( PersonRoleNote personRoleNote );
}
