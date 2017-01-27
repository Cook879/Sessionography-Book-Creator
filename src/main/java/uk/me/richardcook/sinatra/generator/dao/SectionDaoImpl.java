package uk.me.richardcook.sinatra.generator.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.me.richardcook.sinatra.generator.model.Section;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SectionDaoImpl implements SectionDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Section> findAll() {
		return entityManager.createQuery( "SELECT e FROM Section e" ).getResultList();
	}

	public Section find( int id ) {
		return entityManager.find( Section.class, id );
	}

	@Transactional
	public void save( Section section ) {
		entityManager.persist( section );
	}

	@Transactional
	public void update( Section section ) {
		entityManager.merge( section );
	}

	public Section findByPosition( Integer position ) {
		List<Section> sections = entityManager.createQuery( "SELECT e FROM Section e WHERE e.position = :position" )
				                         .setParameter( "position", position )
				                         .getResultList();
		if ( sections.size() > 0 )
			return sections.get( 0 );
		return null;
	}

}
