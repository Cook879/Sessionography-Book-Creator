package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.SessionSong;
import uk.me.richardcook.sessionography.model.SessionSongJoined;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SessionSongDaoImpl implements SessionSongDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<SessionSong> findForSession( int id ) {
		return entityManager.createQuery( "SELECT e FROM SessionSong e WHERE e.session = :id ORDER BY e.position" )
				       .setParameter( "id", id )
				       .getResultList();
	}

	public List<Integer> findIdsForSession( int id ) {
		return entityManager.createQuery( "SELECT e.id FROM SessionSong e WHERE e.session = :id" )
				       .setParameter( "id", id )
				       .getResultList();
	}

	public List<SessionSong> findAll() {
		return entityManager.createQuery( "SELECT e FROM SessionSong e" )
				       .getResultList();
	}

	public SessionSong find( int id ) {
		return entityManager.find( SessionSong.class, id );
	}

	@Transactional
	public void save( SessionSong sessionSong ) {
		entityManager.persist( sessionSong );
	}

	@Transactional
	public void update( SessionSong sessionSong ) {
		entityManager.merge( sessionSong );
	}

	public SessionSong findBySessionPosition( Integer session, Integer position ) {
		List<SessionSong> sessionSongs = entityManager.createQuery( "SELECT e FROM SessionSong e WHERE e.session = :session AND e.position = :position" )
				                                 .setParameter( "session", session )
				                                 .setParameter( "position", position )
				                                 .getResultList();
		if ( sessionSongs.size() > 0 )
			return sessionSongs.get( 0 );
		return null;
	}

	public List<Integer> getIdsForSession( int id ) {
		return entityManager.createQuery( "SELECT e.id FROM SessionSong e WHERE e.session = :session" )
				       .setParameter( "session", id )
				       .getResultList();
	}

	@Transactional
	public void delete( int id ) {
		entityManager.remove( find( id ) );
	}

	public List<SessionSongJoined> findAllJoined() {
		return entityManager.createQuery( "SELECT e FROM SessionSongJoined e" )
				       .getResultList();
	}

	public List<SessionSongJoined> findForSessionJoined( int id ) {
		return entityManager.createQuery( "SELECT e FROM SessionSongJoined e WHERE e.session = :id ORDER BY e.position" )
				       .setParameter( "id", id )
				       .getResultList();
	}

}
