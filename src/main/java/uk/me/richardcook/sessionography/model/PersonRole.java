package uk.me.richardcook.sessionography.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class which represents the personnel entity in the database
 * <p>
 * PersonRole is a joining class, matching a Person-Role combination to a SessionSong
 */
@MappedSuperclass
public class PersonRole implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@Column( name = "id" )
	private Integer id;

	@ManyToOne
	@JoinColumn( name = "person" )
	private Person person;

	@ManyToOne
	@JoinColumn( name = "role" )
	private RoleView role;

	@Column( name = "note" )
	private Integer note;

	public Person getPerson() {
		return person;
	}

	public void setPerson( Person person ) {
		this.person = person;
	}

	public RoleView getRole() {
		return role;
	}

	public void setRole( RoleView role ) {
		this.role = role;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote( Integer note ) {
		this.note = note;
	}

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}
}

