package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sessionography.model.BookString;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookStringDaoImpl implements BookStringDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<BookString> findAll() {
		return entityManager.createQuery( "SELECT e FROM BookString e ORDER BY e.name" )
				       .getResultList();
	}

	public BookString find( int id ) {
		return entityManager.find( BookString.class, id );
	}

	@Transactional
	public void save( BookString bookString ) {
		entityManager.persist( bookString );
	}

	@Transactional
	public void update( BookString bookString ) {
		entityManager.merge( bookString );
	}

	public BookString findByName( String name ) {
		List<BookString> bookStrings = entityManager.createQuery( "SELECT e FROM BookString e WHERE e.name = :name" )
				                               .setParameter( "name", name )
				                               .getResultList();
		if ( bookStrings.size() > 0 )
			return bookStrings.get( 0 );
		return null;
	}
}
