package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.PersonRoleNote;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;


@Repository
public class PersonRoleNoteDaoImpl implements PersonRoleNoteDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<PersonRoleNote> findCollection( Collection<Integer> ids ) {
		return entityManager.createQuery( "SELECT e FROM PersonRoleNote e WHERE e.id IN (:ids)" )
				       .setParameter( "ids", ids )
				       .getResultList();
	}

	public PersonRoleNote find( int id ) {
		return entityManager.find( PersonRoleNote.class, id );
	}

	public List<PersonRoleNote> findForSession( int id ) {
		return entityManager.createQuery( "SELECT e FROM PersonRoleNote e WHERE e.session = :id" )
				       .setParameter( "id", id )
				       .getResultList();
	}

	public List<PersonRoleNote> findAll() {
		return entityManager.createQuery( "SELECT e FROM PersonRoleNote e" )
				       .getResultList();
	}

	@Transactional
	public void save( PersonRoleNote personRoleNote ) {
		entityManager.persist( personRoleNote );
	}

	@Transactional
	public void update( PersonRoleNote personRoleNote ) {
		entityManager.merge( personRoleNote );
	}
}
