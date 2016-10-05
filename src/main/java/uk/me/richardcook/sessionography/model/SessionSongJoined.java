package uk.me.richardcook.sessionography.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table( name = "session_song" )
public class SessionSongJoined implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@Column( name = "id" )
	private int id;

	@Column( name = "session" )
	private int session;

	@OneToMany( fetch = FetchType.EAGER, mappedBy = "sessionSong" )
	@OrderBy( value = "position" )
	private List<SessionSongSongJoined> sessionSongSongs;

	@Column( name = "position" )
	private int position;

	@Column( name = "master" )
	private String master;

	@Column( name = "notes" )
	private String notes;

	// Work around for unable to fetch multiple bags error
	@LazyCollection( LazyCollectionOption.FALSE )
	@OneToMany( mappedBy = "sessionSong" )
	@OrderBy( value = "position" )
	private List<TakeJoinedBook> takes;

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition( int position ) {
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

	public List<TakeJoinedBook> getTakes() {
		return takes;
	}

	public void setTakes( List<TakeJoinedBook> takes ) {
		this.takes = takes;
	}

	public List<SessionSongSongJoined> getSessionSongSongs() {
		return sessionSongSongs;
	}

	public void setSessionSongSongs( List<SessionSongSongJoined> sessionSongSongs ) {
		this.sessionSongSongs = sessionSongSongs;
	}

	public int getSession() {
		return session;
	}

	public void setSession( int session ) {
		this.session = session;
	}

	@Override
	public String toString() {
		String songTitles = "";

		if ( sessionSongSongs.size() > 1 ) {
			for ( int i = 0; i < sessionSongSongs.size(); i++ ) {
				SongJoinedBook song = sessionSongSongs.get( i ).getSong();
				songTitles += song.getShortTitle();
				if ( i != sessionSongSongs.size() - 1 ) {
					songTitles += " / ";
				}
			}
		} else {
			songTitles = sessionSongSongs.get( 0 ).getSong().getTitle();
		}
		return songTitles;
	}

	public String toBookString(String masterStr) {
		String songTitles = toString();
		if ( master != null )
			return BookFile.createSubSection( songTitles + " (" + masterStr + " #" + master + ")" );

		return BookFile.createSubSection( songTitles );
	}

	public String toShortString() {
		String toString;
		if ( sessionSongSongs.size() > 1 )
			toString = toString();
		else
			toString = sessionSongSongs.get( 0 ).getSong().getShortTitle();
		if ( toString.length() > 35 )
			return toString.substring( 0, 34 ) + "\\ldots";
		return toString;
	}

}
