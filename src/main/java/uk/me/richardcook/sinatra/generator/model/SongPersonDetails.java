package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "vw_song_person" )
public class SongPersonDetails {

	@Id
	@Column( name = "id" )
	private int id;

	@Column( name = "person" )
	private int person;

	@Column( name = "role" )
	private String role;

	@Column( name = "song" )
	private int song;

	@Column( name = "year" )
	private String year;

	public int getSong() {
		return song;
	}

	public void setSong( int song ) {
		this.song = song;
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

	public String getYear() {
		return year;
	}

	public void setYear( String year ) {
		this.year = year;
	}
}
