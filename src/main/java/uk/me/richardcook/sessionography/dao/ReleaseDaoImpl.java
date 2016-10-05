package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.Release;
import uk.me.richardcook.sessionography.model.ReleaseJoined;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class ReleaseDaoImpl implements ReleaseDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Release> findAll() {
		return entityManager.createQuery( "SELECT e FROM Release e" )
				       .getResultList();
	}

	public Release find( int id ) {
		return entityManager.find( Release.class, id );
	}

	public ReleaseJoined findJoined( int id ) {
		return entityManager.find( ReleaseJoined.class, id );
	}

	@Transactional
	public void save( Release release ) {
		entityManager.persist( release );
	}

	@Transactional
	public void update( Release release ) {
		entityManager.merge( release );
	}

	public List<Release> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM Release e WHERE e.title LIKE :query ORDER by e.title" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}
}
