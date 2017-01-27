package uk.me.richardcook.sinatra.generator.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sinatra.generator.model.SourceType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class SourceTypeDaoImpl implements SourceTypeDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<SourceType> findAll() {
		return entityManager.createQuery( "SELECT e FROM SourceType e" )
				       .getResultList();
	}

	public SourceType find( int id ) {
		return entityManager.find( SourceType.class, id );
	}

	@Transactional
	public void save( SourceType source ) {
		entityManager.persist( source );
	}

	@Transactional
	public void update( SourceType source ) {
		entityManager.merge( source );
	}

	public SourceType findByName( String name ) {
		List<SourceType> sourceTypes = entityManager.createQuery( "SELECT e FROM SourceType e WHERE e.name = :name" )
				                               .setParameter( "name", name )
				                               .getResultList();
		if ( sourceTypes.size() > 0 )
			return sourceTypes.get( 0 );
		return null;
	}

}
