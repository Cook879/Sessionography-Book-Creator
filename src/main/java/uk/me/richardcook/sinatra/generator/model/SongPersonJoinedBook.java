package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name = "song_person" )
public class SongPersonJoinedBook extends PersonRole {

	@Column( name = "song" )
	private int song;

	public int getSong() {
		return song;
	}

	public void setSong( int song ) {
		this.song = song;
	}
}