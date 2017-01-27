package uk.me.richardcook.sinatra.generator.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sinatra.generator.model.Label;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LabelDaoImpl implements LabelDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Label> findAll() {
		return entityManager.createQuery( "SELECT e FROM Label e ORDER BY e.name" )
				       .getResultList();
	}

	public Label find( int id ) {
		return entityManager.find( Label.class, id );
	}

	@Transactional
	public void save( Label label ) {
		entityManager.persist( label );
	}

	@Transactional
	public void update( Label label ) {
		entityManager.merge( label );
	}

	public Label findByName( String name ) {
		List<Label> labels = entityManager.createQuery( "SELECT e FROM Label e WHERE e.name = :name" )
				                     .setParameter( "name", name )
				                     .getResultList();
		if ( labels.size() > 0 )
			return labels.get( 0 );
		return null;
	}

	public List<Label> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM Label e WHERE e.name LIKE :query ORDER by e.name" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}

}
