package uk.me.richardcook.sessionography.model;

import javax.persistence.*;

@Entity
@Table( name = "song_person" )
public class SongPersonJoined {

	@Id
	@Column( name = "id" )
	private int id;

	@ManyToOne
	@JoinColumn( name = "person" )
	private Person person;

	@ManyToOne
	@JoinColumn( name = "role" )
	private Role role;

	@Column( name = "note" )
	private Integer note;

	@Column( name = "song" )
	private Integer song;

	public Integer getSong() {
		return song;
	}

	public void setSong( Integer song ) {
		this.song = song;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote( Integer note ) {
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

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( SongPersonJoined.class ) )
			return false;
		SongPersonJoined songPerson = (SongPersonJoined) obj;

		if ( id != songPerson.getId() )
			return false;

		if ( ! song.equals( songPerson.getSong() ) )
			return false;

		if ( ! person.equals( songPerson.getPerson() ) )
			return false;

		if ( ! role.equals( songPerson.getRole() ) )
			return false;

		if ( ( note == null && songPerson.getNote() != null ) || ( note != null && ! note.equals( songPerson.getNote() ) ) )
			return false;
		return true;
	}
}