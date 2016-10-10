package uk.me.richardcook.sessionography.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table( name = "session_song_song" )
public class SessionSongSongJoined implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "session_song" )
	private Integer sessionSong;

	@Column( name = "position" )
	private Integer position;

	@ManyToOne
	@JoinColumn( name = "song" )
	private SongJoinedBook song;

	@Column( name = "parody" )
	private boolean parody;

	public Integer getSessionSong() {
		return sessionSong;
	}

	public void setSessionSong( Integer sessionSong ) {
		this.sessionSong = sessionSong;
	}

	public SongJoinedBook getSong() {
		return song;
	}

	public void setSong( SongJoinedBook song ) {
		this.song = song;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition( Integer position ) {
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( SessionSongSongJoined.class ) )
			return false;
		SessionSongSongJoined sessionSongSong = (SessionSongSongJoined) obj;

		if ( id != sessionSongSong.getId() )
			return false;

		if ( ! sessionSong.equals( sessionSongSong.getSessionSong() ) )
			return false;

		if ( ! song.equals( sessionSongSong.getSong() ) )
			return false;

		if ( ! position.equals( sessionSongSong.getPosition() ) )
			return false;

		if ( parody != sessionSongSong.isParody() )
			return false;

		return true;
	}

	public boolean isParody() {
		return parody;
	}

	public void setParody( boolean parody ) {
		this.parody = parody;
	}
}