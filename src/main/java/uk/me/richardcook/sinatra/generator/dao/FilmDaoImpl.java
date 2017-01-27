package uk.me.richardcook.sinatra.generator.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sinatra.generator.model.Film;
import uk.me.richardcook.sinatra.generator.model.FilmJoined;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FilmDaoImpl implements FilmDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Film> findAll() {
		return entityManager.createQuery( "SELECT e FROM Film e ORDER BY e.title" )
				       .getResultList();
	}

	public Film find( int id ) {
		return entityManager.find( Film.class, id );
	}

	@Transactional
	public void save( Film film ) {
		entityManager.persist( film );
	}

	@Transactional
	public void update( Film film ) {
		entityManager.merge( film );
	}

	public List<Film> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM Film e WHERE e.title LIKE :query ORDER by e.title" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}

	public FilmJoined findJoined( int id ) {
		return entityManager.find( FilmJoined.class, id );
	}

}
