package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Person> findAll() {
		return entityManager.createQuery( "SELECT e FROM Person e ORDER BY e.lastName, e.firstName" )
				       .getResultList();
	}

	public Person find( int id ) {
		return entityManager.find( Person.class, id );
	}

	@Transactional
	public void save( Person person ) {
		entityManager.persist( person );
	}

	@Transactional
	public void update( Person person ) {
		entityManager.merge( person );
	}

	public Person findByName( String firstName, String lastName ) {
		List<Person> persons = entityManager.createQuery( "SELECT e FROM Person e WHERE e.firstName = :firstName AND e.lastName = :lastName" )
				                       .setParameter( "firstName", firstName )
				                       .setParameter( "lastName", lastName )
				                       .getResultList();
		if ( persons.size() > 0 )
			return persons.get( 0 );
		return null;
	}

	public List<Person> search( String query ) {
		return entityManager.createQuery( "SELECT e FROM Person e WHERE e.firstName LIKE :query OR e.lastName LIKE :query ORDER by e.firstName, e.lastName" )
				       .setParameter( "query", "%" + query + "%" )
				       .getResultList();
	}
}
