package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.Album;
import uk.me.richardcook.sessionography.model.AlbumJoined;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AlbumDaoImpl implements AlbumDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Album> findAll() {
		return entityManager.createQuery( "SELECT e FROM Album e ORDER BY e.title" )
				       .getResultList();
	}

	public Album find( int id ) {
		return entityManager.find( Album.class, id );
	}

	@Transactional
	public void save( Album album ) {
		entityManager.persist( album );
	}

	@Transactional
	public void update( Album album ) {
		entityManager.merge( album );
	}

	public List<Album> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM Album e WHERE e.title LIKE :query ORDER by e.title" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}

	public AlbumJoined findJoined( int id ) {
		return entityManager.find( AlbumJoined.class, id );
	}

}
