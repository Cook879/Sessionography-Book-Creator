package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class which represents the personnel entity in the database
 * <p>
 * SessionSongPerson is a joining class, matching a Person-RoleView combination to a SessionSong
 */
@Entity
@Table( name = "vw_session_song_person" )
public class SessionSongPersonView extends PersonRole {

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
}