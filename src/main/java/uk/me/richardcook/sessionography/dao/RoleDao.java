package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.Role;

import java.util.List;


public interface RoleDao {

	List<Role> findAll();

	Role find( int id );

	void save( Role person );

	void update( Role person );

	Role findByName( String name );

	Role findByPosition( Integer group, int position );

	Role findByAbbreviation( String abbreviation );

	List<Role> search( String query );
}
