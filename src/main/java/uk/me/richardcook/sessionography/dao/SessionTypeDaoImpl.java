package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.SessionType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class SessionTypeDaoImpl implements SessionTypeDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<SessionType> findAll() {
		return entityManager.createQuery( "SELECT e FROM SessionType e" )
				       .getResultList();
	}

	public SessionType find( int id ) {
		return entityManager.find( SessionType.class, id );
	}

	@Transactional
	public void save( SessionType sessionType ) {
		entityManager.persist( sessionType );
	}

	@Transactional
	public void update( SessionType sessionType ) {
		entityManager.merge( sessionType );
	}

	public SessionType findByName( String name ) {
		List<SessionType> sessionTypes = entityManager.createQuery( "SELECT e FROM SessionType e WHERE e.name = :name" )
				                                 .setParameter( "name", name )
				                                 .getResultList();
		if ( sessionTypes.size() > 0 )
			return sessionTypes.get( 0 );
		return null;
	}

}
