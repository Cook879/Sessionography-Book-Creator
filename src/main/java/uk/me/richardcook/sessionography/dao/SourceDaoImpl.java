package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.Source;
import uk.me.richardcook.sessionography.model.SourceJoined;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class SourceDaoImpl implements SourceDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Source> findAll() {
		return entityManager.createQuery( "SELECT e FROM Source e" )
				       .getResultList();
	}

	public Source find( int id ) {
		return entityManager.find( Source.class, id );
	}

	@Transactional
	public void save( Source source ) {
		entityManager.persist( source );
	}

	@Transactional
	public void update( Source source ) {
		entityManager.merge( source );
	}

	public List<Source> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM Source e WHERE e.name LIKE :query ORDER by e.name" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}

	public SourceJoined findJoined( int id ) {
		return entityManager.find( SourceJoined.class, id );
	}

}
