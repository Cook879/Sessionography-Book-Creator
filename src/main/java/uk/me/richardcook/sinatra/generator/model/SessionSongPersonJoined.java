package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FOR API ONLY - not for submitting!
 * <p>
 * Class which represents the personnel entity in the database
 * <p>
 * SessionSongPerson is a joining class, matching a Person-RoleView combination to a SessionSong
 */
@Entity
@Table( name = "session_song_person" )
public class SessionSongPersonJoined {

	@Id
	@Column( name = "id" )
	private int id;

	@ManyToOne
	@JoinColumn( name = "person" )
	private Person person;

	@ManyToOne
	@JoinColumn( name = "role" )
	private Role role;

	@Column( name = "session_song" )
	private Integer sessionSong;

	@ManyToOne
	@JoinColumn( name = "note" )
	private PersonRoleNote note;

	@Transient
	private List<Integer> sessionSongs = new ArrayList<Integer>();

	public Integer getSessionSong() {
		return sessionSong;
	}

	public void setSessionSong( Integer sessionSong ) {
		this.sessionSong = sessionSong;
	}

	public PersonRoleNote getNote() {
		return note;
	}

	public void setNote( PersonRoleNote note ) {
		this.note = note;
	}

	public Role getRole() {
		return role;
	}

	public void setRole( Role role ) {
		this.role = role;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson( Person person ) {
		this.person = person;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public List<Integer> getSessionSongs() {
		return sessionSongs;
	}

	public void setSessionSongs( List<Integer> sessionSongs ) {
		this.sessionSongs = sessionSongs;
	}

	public void addSessionSong( Integer sessionSong ) {
		sessionSongs.add( sessionSong );
	}
}

