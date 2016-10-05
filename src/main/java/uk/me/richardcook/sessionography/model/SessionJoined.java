package uk.me.richardcook.sessionography.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table( name = "session" )
public class SessionJoined implements Serializable {

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

	@ManyToOne
	@JoinColumn( name = "location" )
	private Location location;

	@ManyToOne
	@JoinColumn( name = "[with]" )
	private With with;

	@ManyToOne
	@JoinColumn( name = "label" )
	private Label label;

	@ManyToOne
	@JoinColumn( name = "for_album" )
	private Album forAlbum;

	@ManyToOne
	@JoinColumn( name = "for_other" )
	private ForOther forOther;

	@Column( name = "type" )
	private Integer type;

	@Column( name = "notes" )
	private String notes;

	@Column( name = "session_number" )
	private String sessionNumber;

	@ManyToOne
	@JoinColumn( name = "film" )
	private Film film;

	@ManyToOne
	@JoinColumn( name = "radio" )
	private Radio radio;

	@ManyToOne
	@JoinColumn( name = "television" )
	private Television television;

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

	public Film getFilm() {
		return film;
	}

	public void setFilm( Film film ) {
		this.film = film;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection( Integer section ) {
		this.section = section;
	}

	public With getWith() {
		return with;
	}

	public void setWith( With with ) {
		this.with = with;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel( Label label ) {
		this.label = label;
	}

	public Album getForAlbum() {
		return forAlbum;
	}

	public void setForAlbum( Album forAlbum ) {
		this.forAlbum = forAlbum;
	}

	public ForOther getForOther() {
		return forOther;
	}

	public void setForOther( ForOther forOther ) {
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
		if ( ! obj.getClass().equals( SessionJoined.class ) )
			return false;
		SessionJoined session = (SessionJoined) obj;

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
		if ( ( film == null && session.getFilm() != null ) || ( film != null && ! film.equals( session.getFilm() ) ) )
			return false;
		if ( ( radio == null && session.getRadio() != null ) || ( radio != null && ! radio.equals( session.getRadio() ) ) )
			return false;
		if ( ( television == null && session.getTelevision() != null ) || ( television != null && ! television.equals( session.getTelevision() ) ) )
			return false;


		return with.equals( session.getWith() );
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
