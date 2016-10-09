package uk.me.richardcook.sessionography.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table( name = "session_song" )
public class SessionSong implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "session" )
	private Integer session;

	@Column( name = "position" )
	private Integer position;

	@Column( name = "master" )
	private String master;

	@Column( name = "notes" )
	private String notes;

	@Column( name = "reprise" )
	private boolean reprise;

	@OneToMany( fetch = FetchType.EAGER, mappedBy = "sessionSong" )
	@OrderBy( value = "position" )
	private List<SessionSongSong> sessionSongSongs;

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition( Integer position ) {
		this.position = position;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster( String master ) {
		this.master = master;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes( String notes ) {
		this.notes = notes;
	}

	public Integer getSession() {
		return session;
	}

	public void setSession( Integer session ) {
		this.session = session;
	}


	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( SessionSong.class ) )
			return false;
		SessionSong sessionSong = (SessionSong) obj;

		if ( id != sessionSong.getId() )
			return false;

		if ( ! position.equals( sessionSong.getPosition() ) )
			return false;

		if ( ( master == null && sessionSong.getMaster() != null ) || ( master != null && ! master.equals( sessionSong.getMaster() ) ) )
			return false;

		if ( ( notes == null && sessionSong.getNotes() != null ) || ( notes != null && ! notes.equals( sessionSong.getNotes() ) ) )
			return false;

		if ( reprise != sessionSong.isReprise() )
			return false;
		return true;
	}

	public List<SessionSongSong> getSessionSongSongs() {
		return sessionSongSongs;
	}

	public void setSessionSongSongs( List<SessionSongSong> sessionSongSongs ) {
		this.sessionSongSongs = sessionSongSongs;
	}

	public boolean isReprise() {
		return reprise;
	}

	public void setReprise( boolean reprise ) {
		this.reprise = reprise;
	}
}
