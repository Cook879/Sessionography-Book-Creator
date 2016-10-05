package uk.me.richardcook.sessionography.dao;

import uk.me.richardcook.sessionography.model.*;

import java.util.List;

public interface PersonRoleDao {

	List<SessionSongPerson> findForSession( List<Integer> ids );

	List<SongPerson> findForSong( int id );

	List<SessionSongPerson> findForSessionSong( Integer id );

	List<SessionSongPersonDetails> findAll();

	List<SessionSongPersonDetails> findForPerson( int id, boolean group, boolean arranger );

	List<SongPersonDetails> findSongForPerson( int id );

	List<SessionSongPersonView> findForSessionView( List<Integer> sessionSongIds );

	List<SessionSongPersonView> findForSessionSongView( int id );

	List<SongPersonJoinedBook> findForSongJoinedBook( int id );
}
