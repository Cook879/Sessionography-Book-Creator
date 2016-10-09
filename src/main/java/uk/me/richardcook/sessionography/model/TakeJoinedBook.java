package uk.me.richardcook.sessionography.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "take" )
public class TakeJoinedBook implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@Column( name = "id" )
	private int id;

	@Column( name = "session_song" )
	private int sessionSong;

	@Column( name = "title" )
	private String title;

	@Column( name = "length" )
	private String length;

	@ManyToOne
	@JoinColumn( name = "original_release" )
	private ReleaseView originalRelease;

	@ManyToOne
	@JoinColumn( name = "key_release" )
	private ReleaseView keyRelease;

	@ManyToOne
	@JoinColumn( name = "film" )
	private FilmView film;

	@ManyToOne
	@JoinColumn( name = "radio" )
	private RadioView radio;

	@ManyToOne
	@JoinColumn( name = "television" )
	private TelevisionView television;

	@Column( name = "notes" )
	private String notes;

	@Column( name = "position" )
	private int position;

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public int getSessionSong() {
		return sessionSong;
	}

	public void setSessionSong( int sessionSong ) {
		this.sessionSong = sessionSong;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle( String title ) {
		this.title = title;
	}

	public String getLength() {
		return length;
	}

	public void setLength( String length ) {
		this.length = length;
	}

	public ReleaseView getOriginalRelease() {
		return originalRelease;
	}

	public void setOriginalRelease( ReleaseView originalRelease ) {
		this.originalRelease = originalRelease;
	}

	public ReleaseView getKeyRelease() {
		return keyRelease;
	}

	public void setKeyRelease( ReleaseView keyRelease ) {
		this.keyRelease = keyRelease;
	}

	public FilmView getFilm() {
		return film;
	}

	public void setFilm( FilmView film ) {
		this.film = film;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes( String notes ) {
		this.notes = notes;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition( int position ) {
		this.position = position;
	}

	public void writeToBook( BookFile book ) {
		book.printSubSubSection( title );

		book.printLabel( book.getBookString( "length" ), length );
		book.printNewLine();

		if ( film != null ) {
			book.printLabel( book.getBookString( "film" ), film.toBookString() );
			book.printNewLine();
		}
		if ( radio != null ) {
			book.printLabel( book.getBookString( "radio" ), radio.toBookString() );
			book.printNewLine();
		}
		if ( television != null ) {
			book.printLabel( book.getBookString( "television" ), television.toBookString() );
			book.printNewLine();
		}

		if ( originalRelease != null && ! originalRelease.equals( keyRelease ) ) {
			book.printLabel( book.getBookString( "original_release" ), originalRelease.toBookString( book ) );
			book.printNewLine();
		}
		if ( keyRelease != null ) {
			book.printLabel( book.getBookString( "key_release" ), keyRelease.toBookString( book ) );
			book.printNewLine();
		}
	}

	public RadioView getRadio() {
		return radio;
	}

	public void setRadio( RadioView radio ) {
		this.radio = radio;
	}

	public TelevisionView getTelevision() {
		return television;
	}

	public void setTelevision( TelevisionView television ) {
		this.television = television;
	}
}
