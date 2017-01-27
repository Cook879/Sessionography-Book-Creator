package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table( name = "session" )
public class Session implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "section" )
	private Integer section;

	@Column( name = "date" )
	private Date date;

	@Column( name = "date_display" )
	private String dateDisplay;

	@Column( name = "location" )
	private Integer location;

	@Column( name = "[with]" )
	private Integer with;

	@Column( name = "label" )
	private Integer label;

	@Column( name = "for_album" )
	private Integer forAlbum;

	@Column( name = "for_other" )
	private Integer forOther;

	@Column( name = "type" )
	private Integer type;

	@Column( name = "notes" )
	private String notes;

	@Column( name = "session_number" )
	private String sessionNumber;

	@Column( name = "radio" )
	private Integer radio;

	@Column( name = "television" )
	private Integer television;

	@Column( name = "film" )
	private Integer film;

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

	public Integer getLocation() {
		return location;
	}

	public void setLocation( Integer location ) {
		this.location = location;
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

	public Integer getFilm() {
		return film;
	}

	public void setFilm( Integer film ) {
		this.film = film;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection( Integer section ) {
		this.section = section;
	}

	public Integer getWith() {
		return with;
	}

	public void setWith( Integer with ) {
		this.with = with;
	}

	public Integer getLabel() {
		return label;
	}

	public void setLabel( Integer label ) {
		this.label = label;
	}

	public Integer getForAlbum() {
		return forAlbum;
	}

	public void setForAlbum( Integer forAlbum ) {
		this.forAlbum = forAlbum;
	}

	public Integer getForOther() {
		return forOther;
	}

	public void setForOther( Integer forOther ) {
		this.forOther = forOther;
	}

	public Integer getType() {
		return type;
	}

	public void setType( Integer type ) {
		this.type = type;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( Session.class ) )
			return false;
		Session session = (Session) obj;

		if ( id != session.getId() )
			return false;

		if ( ! date.equals( session.getDate() ) )
			return false;

		if ( ! dateDisplay.equals( session.getDateDisplay() ) )
			return false;

		if ( ! location.equals( session.getLocation() ) )
			return false;

		if ( ( label == null && session.getLabel() != null ) || ( label != null && ! label.equals( session.getLabel() ) ) )
			return false;

		if ( ( forAlbum == null && session.getForAlbum() != null ) || ( forAlbum != null && ! forAlbum.equals( session.getForAlbum() ) ) )
			return false;

		if ( ( forOther == null && session.getForOther() != null ) || ( forOther != null && ! forOther.equals( session.getForOther() ) ) )
			return false;

		if ( ! type.equals( session.getType() ) )
			return false;

		if ( ( notes == null && session.getNotes() != null ) || ( notes != null && ! notes.equals( session.getNotes() ) ) )
			return false;
		if ( ( sessionNumber == null && session.getSessionNumber() != null ) || ( sessionNumber != null && ! sessionNumber.equals( session.getSessionNumber() ) ) )
			return false;
		if ( ( radio == null && session.getRadio() != null ) || ( radio != null && ! radio.equals( session.getRadio() ) ) )
			return false;
		if ( ( television == null && session.getTelevision() != null ) || ( television != null && ! television.equals( session.getTelevision() ) ) )
			return false;
		if ( ( film == null && session.getFilm() != null ) || ( film != null && ! film.equals( session.getFilm() ) ) )
			return false;

		return with.equals( session.getWith() );
	}

	public Integer getRadio() {
		return radio;
	}

	public void setRadio( Integer radio ) {
		this.radio = radio;
	}

	public Integer getTelevision() {
		return television;
	}

	public void setTelevision( Integer television ) {
		this.television = television;
	}
}
