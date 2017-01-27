package uk.me.richardcook.sinatra.generator.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sinatra.generator.model.Take;
import uk.me.richardcook.sinatra.generator.model.TakeJoined;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class TakeDaoImpl implements TakeDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Take> findAll() {
		return entityManager.createQuery( "SELECT e FROM Take e" )
				       .getResultList();
	}

	public Take find( int id ) {
		return entityManager.find( Take.class, id );
	}

	public TakeJoined findJoined( int id ) {
		return entityManager.find( TakeJoined.class, id );
	}

	@Transactional
	public void delete( int id ) {
		entityManager.remove( find( id ) );
	}

	@Transactional
	public void save( Take take ) {
		entityManager.persist( take );
	}

	@Transactional
	public void update( Take take ) {
		entityManager.merge( take );
	}

	public Take findBySessionSongPosition( Integer sessionSong, Integer position ) {
		List<Take> takes = entityManager.createQuery( "SELECT e FROM Take e WHERE e.sessionSong = :sessionSong AND e.position = :position" )
				                   .setParameter( "sessionSong", sessionSong )
				                   .setParameter( "position", position )
				                   .getResultList();
		if ( takes.size() > 0 )
			return takes.get( 0 );
		return null;
	}

	public List<TakeJoined> findForSessionSong( int id ) {
		return entityManager.createQuery( "SELECT e FROM TakeJoined e WHERE e.sessionSong = :sessionSong" )
				       .setParameter( "sessionSong", id )
				       .getResultList();
	}
}
