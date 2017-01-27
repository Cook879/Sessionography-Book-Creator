package uk.me.richardcook.sinatra.generator.dao;

import uk.me.richardcook.sinatra.generator.model.RoleGroup;
import uk.me.richardcook.sinatra.generator.model.RoleGroupJoined;

import java.util.List;

public interface RoleGroupDao {

	List<RoleGroup> findAll();

	Integer getRoleCount();

	Integer getRoleGroupCount();

	RoleGroup find( int id );

	void save( RoleGroup person );

	void update( RoleGroup person );

	RoleGroup findByName( String name );

	RoleGroup findByPosition( int position );

	List<RoleGroupJoined> findAllJoined();

	List<RoleGroup> findAllByName();
}
