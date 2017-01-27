package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table( name = "take" )
public class Take implements Serializable {

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

	@Column( name = "original_release" )
	private Integer originalRelease;

	@Column( name = "key_release" )
	private Integer keyRelease;

	@Column( name = "film" )
	private Integer film;

	@Column( name = "television" )
	private Integer television;

	@Column( name = "radio" )
	private Integer radio;

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

	public Integer getOriginalRelease() {
		return originalRelease;
	}

	public void setOriginalRelease( Integer originalRelease ) {
		this.originalRelease = originalRelease;
	}

	public Integer getKeyRelease() {
		return keyRelease;
	}

	public void setKeyRelease( Integer keyRelease ) {
		this.keyRelease = keyRelease;
	}

	public Integer getFilm() {
		return film;
	}

	public void setFilm( Integer film ) {
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
		if ( ! obj.getClass().equals( Take.class ) )
			return false;
		Take take = (Take) obj;

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

	public Integer getTelevision() {
		return television;
	}

	public void setTelevision( Integer television ) {
		this.television = television;
	}

	public Integer getRadio() {
		return radio;
	}

	public void setRadio( Integer radio ) {
		this.radio = radio;
	}
}
