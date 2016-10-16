package uk.me.richardcook.sessionography.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Class which represents the personnel entity in the database
 * <p>
 * SessionSongPerson is a joining class, matching a Person-RoleView combination to a SessionSong
 */
@Entity
@Table( name = "vw_session_song_person_details" )
public class SessionSongPersonDetails {

	@Id
	@Column( name = "id" )
	private int id;

	@Column( name = "person" )
	private int person;

	@Column( name = "role" )
	private String role;

	@Column( name = "session_song" )
	private int sessionSong;

	@Column( name = "position" )
	private int sessionSongPosition;

	@Column( name = "arranger" )
	private boolean arranger;

	@Column( name = "group" )
	private boolean group;

	@Column( name = "writer" )
	private boolean writer;

	@Column( name = "date" )
	private Date date;

	@Column( name = "section" )
	private int section;

	@Column( name = "role_position" )
	private int rolePosition;

	@Column( name = "role_group_position" )
	private int roleGroupPosition;

	public int getSessionSong() {
		return sessionSong;
	}

	public void setSessionSong( int sessionSong ) {
		this.sessionSong = sessionSong;
	}

	public int getSessionSongPosition() {
		return sessionSongPosition;
	}

	public void setSessionSongPosition( int sessionSongPosition ) {
		this.sessionSongPosition = sessionSongPosition;
	}

	public boolean isGroup() {
		return group;
	}

	public void setGroup( boolean group ) {
		this.group = group;
	}

	public boolean isArranger() {
		return arranger;
	}

	public void setArranger( boolean arranger ) {
		this.arranger = arranger;
	}

	public boolean isWriter() {
		return writer;
	}

	public void setWriter( boolean writer ) {
		this.writer = writer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate( Date date ) {
		this.date = date;
	}

	public String getRole() {
		return role;
	}

	public void setRole( String role ) {
		this.role = role;
	}

	public int getPerson() {
		return person;
	}

	public void setPerson( int person ) {
		this.person = person;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public int getSection() {
		return section;
	}

	public void setSection( int section ) {
		this.section = section;
	}

	public int getRolePosition() {
		return rolePosition;
	}

	public void setRolePosition( int rolePosition ) {
		this.rolePosition = rolePosition;
	}

	public int getRoleGroupPosition() {
		return roleGroupPosition;
	}

	public void setRoleGroupPosition( int roleGroupPosition ) {
		this.roleGroupPosition = roleGroupPosition;
	}
}