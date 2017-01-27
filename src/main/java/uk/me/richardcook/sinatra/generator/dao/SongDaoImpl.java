package uk.me.richardcook.sinatra.generator.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sinatra.generator.model.Song;
import uk.me.richardcook.sinatra.generator.model.SongJoined;
import uk.me.richardcook.sinatra.generator.model.SongJoinedBook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SongDaoImpl implements SongDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Song> findAll() {
		return entityManager.createQuery( "SELECT e FROM Song e ORDER BY e.shortTitle" )
				       .getResultList();
	}

	public List<SongJoined> findAllJoined() {
		return entityManager.createQuery( "SELECT e FROM SongJoined e ORDER BY e.shortTitle" )
				       .getResultList();
	}

	public Song find( int id ) {
		return entityManager.find( Song.class, id );
	}

	@Transactional
	public void save( Song song ) {
		entityManager.persist( song );
	}

	@Transactional
	public void update( Song song ) {
		entityManager.merge( song );
	}

	public Song findByTitle( String title ) {
		List<Song> songs = entityManager.createQuery( "SELECT e FROM Song e WHERE e.title = :title" )
				                   .setParameter( "title", title )
				                   .getResultList();
		if ( songs.size() > 0 )
			return songs.get( 0 );
		return null;
	}

	public List<Song> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM Song e WHERE e.title LIKE :query OR e.shortTitle LIKE :query ORDER by e.shortTitle" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}

	public SongJoined findJoined( int id ) {
		return entityManager.find( SongJoined.class, id );
	}

	public List<SongJoinedBook> findAllJoinedBook() {
		return entityManager.createQuery( "SELECT e FROM SongJoinedBook e ORDER BY e.shortTitle" )
				       .getResultList();
	}


}
