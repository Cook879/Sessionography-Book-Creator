package uk.me.richardcook.sessionography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.me.richardcook.sessionography.dao.SessionDao;
import uk.me.richardcook.sessionography.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookSessionService {

	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private BookSessionSongService bookSessionSongService;

	@Autowired
	private BookPersonRoleService bookPersonRoleService;

	public List<SessionView> findForSection( int id ) {
		return sessionDao.findForSection( id );
	}

	public void writeForSection( BookFile book, int id ) {
		List<SessionView> sessions = findForSection( id );

		for ( SessionView session : sessions ) {
			book.printNewPage();

			String sessionNumber = session.getSessionNumber();
			String dateDisplay = session.getDateDisplay();
			if ( sessionNumber != null ) {
				book.printSection( dateDisplay + " (" + book.getBookString( "session" ) + " #" + sessionNumber + ")" );
			} else {
				book.printSection( dateDisplay );
			}

			book.println( session.getWith() );
			book.printNewLine();

			AlbumView album = session.getAlbum();
			FilmView film = session.getFilm();
			TelevisionView television = session.getTelevision();
			RadioView radio = session.getRadio();
			ForOther other = session.getOther();
			String label = session.getLabel();

			if ( album != null ) {
				book.printLabel( book.getBookString( "for_album" ), album.toBookString() );
				book.printNewLine();
			} else if ( label != null ) {
				book.printLabel( book.getBookString( "for" ), label );
				book.printNewLine();
			} else if ( film != null ) {
				book.printLabel( book.getBookString( "for_film" ), film.toBookString() );
				book.printNewLine();
			} else if ( radio != null ) {
				book.printLabel( book.getBookString( "for_radio" ), radio.toBookString() );
				book.printNewLine();
			} else if ( television != null ) {
				book.printLabel( book.getBookString( "for_television" ), television.toBookString() );
				book.printNewLine();
			} else if ( other != null ) {
				book.printLabel( book.getBookString( "for" ), other.getDescription() );
				book.printNewLine();
			}

			session.getLocation().writeToBook( book );

			bookPersonRoleService.writeForSession( book, session.getId() );

			bookSessionSongService.writeForSession( book, session.getId() );

			String notes = session.getNotes();
			if ( notes != null ) {
				book.printNewLine();
				book.printNewLine();
				book.printNewLine();
				book.printLabel( book.getBookString( "note" ), notes );
			}
		}
	}

	public Map<Integer, SessionView> findAllMap() {
		List<SessionView> songs = findAll();
		Map<Integer, SessionView> map = new HashMap<Integer, SessionView>();
		for ( SessionView session : songs ) {
			map.put( session.getId(), session );
		}
		return map;

	}

	private List<SessionView> findAll() {
		return sessionDao.findAllView();
	}
}
