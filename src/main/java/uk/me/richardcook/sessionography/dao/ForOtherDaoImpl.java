package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.ForOther;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ForOtherDaoImpl implements ForOtherDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<ForOther> findAll() {
		return entityManager.createQuery( "SELECT e FROM ForOther e" )
				       .getResultList();
	}

	public ForOther find( int id ) {
		return entityManager.find( ForOther.class, id );
	}

	@Transactional
	public void save( ForOther forOther ) {
		entityManager.persist( forOther );
	}

	@Transactional
	public void update( ForOther forOther ) {
		entityManager.merge( forOther );
	}

	public List<ForOther> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM ForOther e WHERE e.description LIKE :query ORDER by e.description" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}

}
