package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Role> findAll() {
		return entityManager.createQuery( "SELECT e FROM Role e ORDER BY e.name" )
				       .getResultList();
	}

	public Role find( int id ) {
		return entityManager.find( Role.class, id );
	}

	@Transactional
	public void save( Role role ) {
		entityManager.persist( role );
	}

	@Transactional
	public void update( Role role ) {
		entityManager.merge( role );
	}

	public Role findByName( String name ) {
		List<Role> roles = entityManager.createQuery( "SELECT e FROM Role e WHERE e.name = :name" )
				                   .setParameter( "name", name )
				                   .getResultList();
		if ( roles.size() > 0 )
			return roles.get( 0 );
		return null;
	}

	public Role findByPosition( Integer group, int position ) {
		List<Role> roles = entityManager.createQuery( "SELECT e FROM Role e WHERE e.group = :group AND e.position = :position" )
				                   .setParameter( "group", group )
				                   .setParameter( "position", position )
				                   .getResultList();
		if ( roles.size() > 0 )
			return roles.get( 0 );
		return null;
	}

	public Role findByAbbreviation( String abbreviation ) {
		List<Role> roles = entityManager.createQuery( "SELECT e FROM Role e WHERE e.abbreviation = :abbreviation" )
				                   .setParameter( "abbreviation", abbreviation )
				                   .getResultList();
		if ( roles.size() > 0 )
			return roles.get( 0 );
		return null;
	}

	public List<Role> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM Role e WHERE e.name LIKE :query OR e.abbreviation Like :query ORDER by e.name" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}
}
