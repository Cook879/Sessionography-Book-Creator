package uk.me.richardcook.sinatra.generator.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sinatra.generator.model.SongPerson;
import uk.me.richardcook.sinatra.generator.model.SongPersonJoined;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class SongPersonDaoImpl implements SongPersonDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<SongPerson> findAll() {
		return entityManager.createQuery( "SELECT e FROM SongPerson e" )
				       .getResultList();
	}

	public SongPerson find( int id ) {
		return entityManager.find( SongPerson.class, id );
	}

	@Transactional
	public void save( SongPerson songPerson ) {
		entityManager.persist( songPerson );
	}

	@Transactional
	public void update( SongPerson songPerson ) {
		entityManager.merge( songPerson );
	}

	public SongPerson findBySongPersonRole( Integer song, Integer person, Integer role ) {
		List<SongPerson> songPersons = entityManager.createQuery( "SELECT e FROM SongPerson e WHERE e.song = :song AND e.person = :person AND e.role = :role" )
				                               .setParameter( "song", song )
				                               .setParameter( "person", person )
				                               .setParameter( "role", role )
				                               .getResultList();
		if ( songPersons.size() > 0 )
			return songPersons.get( 0 );
		return null;
	}

	public List<SongPersonJoined> findForSong( int id ) {
		return entityManager.createQuery( "SELECT e FROM SongPersonJoined e WHERE e.song = :song" )
				       .setParameter( "song", id )
				       .getResultList();
	}

	@Transactional
	public void deleteForSong( int id ) {
		entityManager.createQuery( "DELETE FROM SongPerson e WHERE e.song = :song" )
				.setParameter( "song", id )
				.executeUpdate();
	}

}
