package uk.me.richardcook.sinatra.generator.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sinatra.generator.model.With;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class WithDaoImpl implements WithDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<With> findAll() {
		return entityManager.createQuery( "SELECT e FROM With e ORDER BY e.name" )
				       .getResultList();
	}

	public With find( int id ) {
		return entityManager.find( With.class, id );
	}

	@Transactional
	public void save( With with ) {
		entityManager.persist( with );
	}

	@Transactional
	public void update( With with ) {
		entityManager.merge( with );
	}

	public With findByName( String name ) {
		List<With> withs = entityManager.createQuery( "SELECT e FROM With e WHERE e.name = :name" )
				                   .setParameter( "name", name )
				                   .getResultList();
		if ( withs.size() > 0 )
			return withs.get( 0 );
		return null;
	}

	public List<With> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM With e WHERE e.name LIKE :query ORDER by e.name" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}

}
