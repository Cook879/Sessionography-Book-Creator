package uk.me.richardcook.sessionography.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table( name = "take" )
public class TakeJoined implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "session_song" )
	private Integer sessionSong;

	@Column( name = "title" )
	private String title;

	@Column( name = "length" )
	private String length;

	@ManyToOne
	@JoinColumn( name = "original_release" )
	private Release originalRelease;

	@ManyToOne
	@JoinColumn( name = "key_release" )
	private Release keyRelease;

	@ManyToOne
	@JoinColumn( name = "film" )
	private Film film;

	@ManyToOne
	@JoinColumn( name = "radio" )
	private Radio radio;

	@ManyToOne
	@JoinColumn( name = "television" )
	private Television television;

	@Column( name = "notes" )
	private String notes;

	@Column( name = "position" )
	private Integer position;

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public Integer getSessionSong() {
		return sessionSong;
	}

	public void setSessionSong( Integer sessionSong ) {
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

	public Release getOriginalRelease() {
		return originalRelease;
	}

	public void setOriginalRelease( Release originalRelease ) {
		this.originalRelease = originalRelease;
	}

	public Release getKeyRelease() {
		return keyRelease;
	}

	public void setKeyRelease( Release keyRelease ) {
		this.keyRelease = keyRelease;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm( Film film ) {
		this.film = film;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes( String notes ) {
		this.notes = notes;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition( Integer position ) {
		this.position = position;
	}


	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( TakeJoined.class ) )
			return false;
		TakeJoined take = (TakeJoined) obj;

		if ( id != take.getId() )
			return false;

		if ( ! sessionSong.equals( take.getSessionSong() ) )
			return false;

		if ( ! title.equals( take.getTitle() ) )
			return false;

		if ( ! length.equals( take.getLength() ) )
			return false;

		if ( ! originalRelease.equals( take.getOriginalRelease() ) )
			return false;

		if ( ! keyRelease.equals( take.getKeyRelease() ) )
			return false;


		if ( ( notes == null && take.getNotes() != null ) || ( notes != null && ! notes.equals( take.getNotes() ) ) )
			return false;

		if ( ( film == null && take.getFilm() != null ) || ( film != null && ! film.equals( take.getFilm() ) ) )
			return false;

		if ( ( radio == null && take.getRadio() != null ) || ( radio != null && ! radio.equals( take.getRadio() ) ) )
			return false;
		if ( ( television == null && take.getTelevision() != null ) || ( television != null && ! television.equals( take.getTelevision() ) ) )
			return false;


		if ( ! position.equals( take.getPosition() ) )
			return false;

		return true;
	}

	public Radio getRadio() {
		return radio;
	}

	public void setRadio( Radio radio ) {
		this.radio = radio;
	}

	public Television getTelevision() {
		return television;
	}

	public void setTelevision( Television television ) {
		this.television = television;
	}
}
