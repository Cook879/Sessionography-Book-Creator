package uk.me.richardcook.sessionography.dao;

import org.springframework.stereotype.Repository;
import uk.me.richardcook.sessionography.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonRoleDaoImpl implements PersonRoleDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<SessionSongPerson> findForSession( List<Integer> ids ) {
		return entityManager.createQuery( "SELECT e FROM SessionSongPerson e WHERE e.sessionSong IN (:ids) AND e.role.arranger = 0" )
				       .setParameter( "ids", ids )
				       .getResultList();
	}

	public List<SongPerson> findForSong( int id ) {
		return entityManager.createQuery( "SELECT e FROM SongPerson e WHERE e.song = :id" )
				       .setParameter( "id", id )
				       .getResultList();
	}

	public List<SessionSongPerson> findForSessionSong( Integer id ) {
		return entityManager.createQuery( "SELECT e FROM SessionSongPerson e WHERE e.sessionSong = :id AND e.role.arranger = 1" )
				       .setParameter( "id", id )
				       .getResultList();
	}

	public List<SessionSongPersonDetails> findAll() {
		return entityManager.createQuery( "SELECT e FROM SessionSongPersonDetails e WHERE e.group = 0 AND e.arranger = 0 AND e.writer = 0" )
				       .getResultList();
	}

	public List<SessionSongPersonDetails> findForPerson( int id, boolean group, boolean arranger ) {
		return entityManager.createQuery( "SELECT e FROM SessionSongPersonDetails e WHERE e.person = :person" +
				                                  " AND e.group = :group AND e.arranger = :arranger ORDER BY e.date, e.sessionSongPosition, e.roleGroupPosition, e.rolePosition" )
				       .setParameter( "person", id )
				       .setParameter( "group", group )
				       .setParameter( "arranger", arranger )
				       .getResultList();
	}

	public List<SongPersonDetails> findSongForPerson( int id ) {
		return entityManager.createQuery( "SELECT e FROM SongPersonDetails e WHERE e.person = :person ORDER BY e.year " )
				       .setParameter( "person", id )
				       .getResultList();
	}

	public List<SessionSongPersonView> findForSessionView( List<Integer> sessionSongIds ) {
		return entityManager.createQuery( "SELECT e FROM SessionSongPersonView e WHERE e.sessionSong IN (:ids) AND e.arranger = 0" )
				       .setParameter( "ids", sessionSongIds )
				       .getResultList();
	}

	public List<SessionSongPersonView> findForSessionSongView( int id ) {
		return entityManager.createQuery( "SELECT e FROM SessionSongPersonView e WHERE e.sessionSong = :id AND e.arranger = 1" )
				       .setParameter( "id", id )
				       .getResultList();
	}

	public List<SongPersonJoinedBook> findForSongJoinedBook( int id ) {
		return entityManager.createQuery( "SELECT e FROM SongPersonJoinedBook e WHERE e.song = :id" )
				       .setParameter( "id", id )
				       .getResultList();
	}

}
