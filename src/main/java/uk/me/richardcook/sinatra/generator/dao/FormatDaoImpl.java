package uk.me.richardcook.sinatra.generator.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sinatra.generator.model.Format;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FormatDaoImpl implements FormatDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Format> findAll() {
		return entityManager.createQuery( "SELECT e FROM Format e" )
				       .getResultList();
	}

	public Format find( int id ) {
		return entityManager.find( Format.class, id );
	}

	@Transactional
	public void save( Format format ) {
		entityManager.persist( format );
	}

	@Transactional
	public void update( Format format ) {
		entityManager.merge( format );
	}

	public Format findByName( String name ) {
		List<Format> formats = entityManager.createQuery( "SELECT e FROM Format e WHERE e.name = :name" )
				                       .setParameter( "name", name )
				                       .getResultList();
		if ( formats.size() > 0 )
			return formats.get( 0 );
		return null;
	}

}
