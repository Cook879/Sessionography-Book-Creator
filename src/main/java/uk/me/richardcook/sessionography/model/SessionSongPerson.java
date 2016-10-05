package uk.me.richardcook.sessionography.model;

import javax.persistence.*;

/**
 * Class which represents the personnel entity in the database
 * <p>
 * SessionSongPerson is a joining class, matching a Person-RoleView combination to a SessionSong
 */
@Entity
@Table( name = "session_song_person" )
public class SessionSongPerson {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "person" )
	private Integer person;

	@Column( name = "role" )
	private Integer role;

	@Column( name = "session_song" )
	private Integer sessionSong;

	@Column( name = "note" )
	private Integer note;

	public Integer getSessionSong() {
		return sessionSong;
	}

	public void setSessionSong( Integer sessionSong ) {
		this.sessionSong = sessionSong;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote( Integer note ) {
		this.note = note;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole( Integer role ) {
		this.role = role;
	}

	public Integer getPerson() {
		return person;
	}

	public void setPerson( Integer person ) {
		this.person = person;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( SessionSongPerson.class ) )
			return false;
		SessionSongPerson sessionSongPerson = (SessionSongPerson) obj;

		if ( id != sessionSongPerson.getId() )
			return false;

		if ( ! sessionSong.equals( sessionSongPerson.getSessionSong() ) )
			return false;

		if ( ! person.equals( sessionSongPerson.getPerson() ) )
			return false;

		if ( ! role.equals( sessionSongPerson.getRole() ) )
			return false;

		if ( ( note == null && sessionSongPerson.getNote() != null ) || ( note != null && ! note.equals( sessionSongPerson.getNote() ) ) )
			return false;
		return true;
	}

}

