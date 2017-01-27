package uk.me.richardcook.sinatra.generator.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sinatra.generator.model.SessionSongPerson;
import uk.me.richardcook.sinatra.generator.model.SessionSongPersonJoined;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class SessionSongPersonDaoImpl implements SessionSongPersonDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<SessionSongPerson> findAll() {
		return entityManager.createQuery( "SELECT e FROM SessionSongPerson e" )
				       .getResultList();
	}

	public SessionSongPerson find( int id ) {
		return entityManager.find( SessionSongPerson.class, id );
	}

	@Transactional
	public void save( SessionSongPerson sessionSongPerson ) {
		entityManager.persist( sessionSongPerson );
	}

	@Transactional
	public void update( SessionSongPerson sessionSongPerson ) {
		entityManager.merge( sessionSongPerson );
	}

	public SessionSongPerson findBySessionSongPersonRole( Integer sessionSong, Integer person, Integer role ) {
		List<SessionSongPerson> sessionSongPersons = entityManager.createQuery( "SELECT e FROM SessionSongPerson e WHERE e.sessionSong = :sessionSong AND e.person = :person AND e.role = :role" )
				                                             .setParameter( "sessionSong", sessionSong )
				                                             .setParameter( "person", person )
				                                             .setParameter( "role", role )
				                                             .getResultList();
		if ( sessionSongPersons.size() > 0 )
			return sessionSongPersons.get( 0 );
		return null;
	}

	public List<SessionSongPersonJoined> findForSession( List<Integer> ids ) {
		return entityManager.createQuery( "SELECT e FROM SessionSongPersonJoined e WHERE e.sessionSong IN :sessions" )
				       .setParameter( "sessions", ids )
				       .getResultList();
	}

	@Transactional
	public void deleteForSession( List<Integer> ids ) {
		entityManager.createQuery( "DELETE FROM SessionSongPerson e WHERE e.sessionSong IN :sessions" )
				.setParameter( "sessions", ids )
				.executeUpdate();
	}

}
