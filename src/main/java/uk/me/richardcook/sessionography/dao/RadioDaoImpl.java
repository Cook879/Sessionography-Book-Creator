package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.Radio;
import uk.me.richardcook.sessionography.model.RadioJoined;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class RadioDaoImpl implements RadioDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Radio> findAll() {
		return entityManager.createQuery( "SELECT e FROM Radio e ORDER BY e.title" )
				       .getResultList();
	}

	public Radio find( int id ) {
		return entityManager.find( Radio.class, id );
	}

	@Transactional
	public void save( Radio radio ) {
		entityManager.persist( radio );
	}

	@Transactional
	public void update( Radio radio ) {
		entityManager.merge( radio );
	}

	public List<Radio> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM Radio e WHERE e.title LIKE :query ORDER by e.title" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}

	public RadioJoined findJoined( int id ) {
		return entityManager.find( RadioJoined.class, id );
	}

}
