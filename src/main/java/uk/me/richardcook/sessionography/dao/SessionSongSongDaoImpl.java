package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.SessionSongSong;
import uk.me.richardcook.sessionography.model.SessionSongSongJoined;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class SessionSongSongDaoImpl implements SessionSongSongDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<SessionSongSong> findAll() {
		return entityManager.createQuery( "SELECT e FROM SessionSongSong e" )
				       .getResultList();
	}

	public SessionSongSong find( int id ) {
		return entityManager.find( SessionSongSong.class, id );
	}

	@Transactional
	public void save( SessionSongSong sessionSongSong ) {
		entityManager.persist( sessionSongSong );
	}

	@Transactional
	public void update( SessionSongSong sessionSongSong ) {
		entityManager.merge( sessionSongSong );
	}

	public SessionSongSong findBySessionSongPosition( Integer sessionSong, Integer position ) {
		List<SessionSongSong> sessionSongSongs = entityManager.createQuery( "SELECT e FROM SessionSongSong e WHERE e.sessionSong = :sessionSong AND e.position = :position" )
				                                         .setParameter( "sessionSong", sessionSong )
				                                         .setParameter( "position", position )
				                                         .getResultList();
		if ( sessionSongSongs.size() > 0 )
			return sessionSongSongs.get( 0 );
		return null;
	}

	public List<SessionSongSongJoined> findForSessionSong( int id ) {
		return entityManager.createQuery( "SELECT e FROM SessionSongSongJoined e WHERE e.sessionSong = :sessionSong" )
				       .setParameter( "sessionSong", id )
				       .getResultList();
	}


	@Transactional
	public void deleteForSessionSong( int id ) {
		entityManager.createQuery( "DELETE FROM SessionSongSong e WHERE e.sessionSong = :session" )
				.setParameter( "session", id )
				.executeUpdate();
	}

}
