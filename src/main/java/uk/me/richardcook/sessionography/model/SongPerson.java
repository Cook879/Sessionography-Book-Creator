package uk.me.richardcook.sessionography.model;

import javax.persistence.*;

@Entity
@Table( name = "song_person" )
public class SongPerson {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "person" )
	private Integer person;

	@Column( name = "role" )
	private Integer role;

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

	public Integer getRole() {
		return role;
	}

	public void setRole( Integer role ) {
		this.role = role;
	}

	public Integer getPerson() {
		return person;
	}

	public void setPerson( Integer person ) {
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
		if ( ! obj.getClass().equals( SongPerson.class ) )
			return false;
		SongPerson songPerson = (SongPerson) obj;

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