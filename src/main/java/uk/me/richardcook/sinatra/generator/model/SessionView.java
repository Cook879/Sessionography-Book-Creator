package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table( name = "vw_session" )
public class SessionView implements Serializable {

	public static final long serialVersionUID = 1L;
	@Id
	@Column( name = "id" )
	private int id;

	@Column( name = "date" )
	private Date date;

	@Column( name = "date_display" )
	private String dateDisplay;

	@ManyToOne
	@JoinColumn( name = "location" )
	private Location location;

	@Column( name = "with" )
	private String with;

	@Column( name = "label" )
	private String label;

	@ManyToOne
	@JoinColumn( name = "album" )
	private AlbumView album;

	@ManyToOne
	@JoinColumn( name = "other" )
	private ForOther other;

	@Column( name = "type" )
	private String type;

	@Column( name = "notes" )
	private String notes;

	@Column( name = "session_number" )
	private String sessionNumber;

	@ManyToOne
	@JoinColumn( name = "radio" )
	private RadioView radio;

	@ManyToOne
	@JoinColumn( name = "television" )
	private TelevisionView television;

	@ManyToOne
	@JoinColumn( name = "film" )
	private FilmView film;

	@Column( name = "section" )
	private int section;

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate( Date date ) {
		this.date = date;
	}

	public String getDateDisplay() {
		return dateDisplay;
	}

	public void setDateDisplay( String dateDisplay ) {
		this.dateDisplay = dateDisplay;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation( Location location ) {
		this.location = location;
	}

	public String getWith() {
		return with;
	}

	public void setWith( String with ) {
		this.with = with;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel( String label ) {
		this.label = label;
	}

	public AlbumView getAlbum() {
		return album;
	}

	public void setAlbum( AlbumView album ) {
		this.album = album;
	}

	public ForOther getOther() {
		return other;
	}

	public void setOther( ForOther other ) {
		this.other = other;
	}

	public String getType() {
		return type;
	}

	public void setType( String type ) {
		this.type = type;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes( String notes ) {
		this.notes = notes;
	}

	public String getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber( String sessionNumber ) {
		this.sessionNumber = sessionNumber;
	}

	public FilmView getFilm() {
		return film;
	}

	public void setFilm( FilmView film ) {
		this.film = film;
	}

	public int getSection() {
		return section;
	}

	public void setSection( int section ) {
		this.section = section;
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
