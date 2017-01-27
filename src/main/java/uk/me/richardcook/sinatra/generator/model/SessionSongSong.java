package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table( name = "session_song_song" )
public class SessionSongSong implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "session_song" )
	private Integer sessionSong;

	@Column( name = "position" )
	private Integer position;

	@Column( name = "song" )
	private Integer song;

	@Column( name = "parody" )
	private boolean parody;

	public Integer getSessionSong() {
		return sessionSong;
	}

	public void setSessionSong( Integer sessionSong ) {
		this.sessionSong = sessionSong;
	}

	public Integer getSong() {
		return song;
	}

	public void setSong( Integer song ) {
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
		if ( ! obj.getClass().equals( SessionSongSong.class ) )
			return false;
		SessionSongSong sessionSongSong = (SessionSongSong) obj;

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