package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.Television;
import uk.me.richardcook.sessionography.model.TelevisionJoined;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class TelevisionDaoImpl implements TelevisionDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Television> findAll() {
		return entityManager.createQuery( "SELECT e FROM Television e ORDER BY e.title" )
				       .getResultList();
	}

	public Television find( int id ) {
		return entityManager.find( Television.class, id );
	}

	@Transactional
	public void save( Television television ) {
		entityManager.persist( television );
	}

	@Transactional
	public void update( Television television ) {
		entityManager.merge( television );
	}

	public List<Television> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM Television e WHERE e.title LIKE :query ORDER by e.title" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}

	public TelevisionJoined findJoined( int id ) {
		return entityManager.find( TelevisionJoined.class, id );
	}

}
