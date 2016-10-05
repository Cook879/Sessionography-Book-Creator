package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.RoleGroup;
import uk.me.richardcook.sessionography.model.RoleGroupJoined;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleGroupDaoImpl implements RoleGroupDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<RoleGroup> findAll() {
		return entityManager.createQuery( "SELECT e FROM RoleGroup e ORDER BY e.position" )
				       .getResultList();
	}

	public Integer getRoleCount() {
		return entityManager.createQuery( "SELECT e FROM Role e" ).getResultList().size();
	}

	public Integer getRoleGroupCount() {
		return entityManager.createQuery( "SELECT COUNT(e) FROM RoleGroup e" ).getResultList().size();
	}

	public List<RoleGroup> findAllByName() {
		return entityManager.createQuery( "SELECT e FROM RoleGroup e ORDER BY e.name" )
				       .getResultList();
	}

	public RoleGroup find( int id ) {
		return entityManager.find( RoleGroup.class, id );
	}

	@Transactional
	public void save( RoleGroup roleGroup ) {
		entityManager.persist( roleGroup );
	}

	@Transactional
	public void update( RoleGroup roleGroup ) {
		entityManager.merge( roleGroup );
	}

	public RoleGroup findByName( String name ) {
		List<RoleGroup> roleGroups = entityManager.createQuery( "SELECT e FROM RoleGroup e WHERE e.name = :name" )
				                             .setParameter( "name", name )
				                             .getResultList();
		if ( roleGroups.size() > 0 )
			return roleGroups.get( 0 );
		return null;
	}

	public RoleGroup findByPosition( int position ) {
		List<RoleGroup> roleGroups = entityManager.createQuery( "SELECT e FROM RoleGroup e WHERE e.position = :position" )
				                             .setParameter( "position", position )
				                             .getResultList();
		if ( roleGroups.size() > 0 )
			return roleGroups.get( 0 );
		return null;
	}

	public List<RoleGroupJoined> findAllJoined() {
		return entityManager.createQuery( "SELECT e FROM RoleGroupJoined e ORDER BY e.name" )
				       .getResultList();
	}
}
