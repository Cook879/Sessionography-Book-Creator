package uk.me.richardcook.sinatra.generator.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sinatra.generator.model.Studio;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class StudioDaoImpl implements StudioDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Studio> findAll() {
		return entityManager.createQuery( "SELECT e FROM Studio e" )
				       .getResultList();
	}

	public Studio find( int id ) {
		return entityManager.find( Studio.class, id );
	}

	@Transactional
	public void save( Studio studio ) {
		entityManager.persist( studio );
	}

	@Transactional
	public void update( Studio studio ) {
		entityManager.merge( studio );
	}

	public Studio findByName( String name ) {
		List<Studio> studios = entityManager.createQuery( "SELECT e FROM Source e WHERE e.name = :name" )
				                       .setParameter( "name", name )
				                       .getResultList();
		if ( studios.size() > 0 )
			return studios.get( 0 );
		return null;
	}

	public List<Studio> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM Studio e WHERE e.name LIKE :query ORDER by e.name" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}
}
