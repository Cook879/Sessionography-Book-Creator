package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.Session;
import uk.me.richardcook.sessionography.model.SessionJoined;
import uk.me.richardcook.sessionography.model.SessionView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionDaoImpl implements SessionDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<SessionView> findForSection( int id ) {
		return entityManager.createQuery( "SELECT e FROM SessionView e WHERE e.section = :id ORDER BY e.date" )
				       .setParameter( "id", id )
				       .getResultList();
	}

	public List<SessionView> findAllView() {
		return entityManager.createQuery( "SELECT e FROM SessionView e" )
				       .getResultList();
	}

	public List<Session> findAll() {
		return entityManager.createQuery( "SELECT e FROM Session e" )
				       .getResultList();
	}

	public Session find( int id ) {
		return entityManager.find( Session.class, id );
	}

	@Transactional
	public void save( Session session ) {
		entityManager.persist( session );
	}

	@Transactional
	public void update( Session session ) {
		entityManager.merge( session );
	}

	public SessionJoined findJoined( int id ) {
		return entityManager.find( SessionJoined.class, id );
	}


	public List<Session> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM Session e WHERE e.dateDisplay LIKE :query ORDER by e.date" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}
}

