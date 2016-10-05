package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.Location;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class LocationDaoImpl implements LocationDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Location> findAll() {
		return entityManager.createQuery( "SELECT e FROM Location e ORDER BY e.name" )
				       .getResultList();
	}

	public Location find( int id ) {
		return entityManager.find( Location.class, id );
	}

	@Transactional
	public void save( Location location ) {
		entityManager.persist( location );
	}

	@Transactional
	public void update( Location location ) {
		entityManager.merge( location );
	}

	public Location findByName( String name ) {
		List<Location> locations = entityManager.createQuery( "SELECT e FROM Location e WHERE e.name = :name" )
				                           .setParameter( "name", name )
				                           .getResultList();
		if ( locations.size() > 0 )
			return locations.get( 0 );
		return null;
	}

	public List<Location> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM Location e WHERE e.name LIKE :query ORDER by e.name" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}
}
