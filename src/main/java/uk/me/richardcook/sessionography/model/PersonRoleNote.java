package uk.me.richardcook.sessionography.model;

import javax.persistence.*;

@Entity
@Table( name = "person_role_note" )
public class PersonRoleNote {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private Integer id;

	@Column( name = "note" )
	private String note;

	@Column( name = "session" )
	private Integer session;

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote( String note ) {
		this.note = note;
	}

	@Override
	public String toString() {
		return note;
	}

	public Integer getSession() {
		return session;
	}

	public void setSession( Integer session ) {
		this.session = session;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( PersonRoleNote.class ) )
			return false;
		PersonRoleNote personRoleNote = (PersonRoleNote) obj;

		return id.equals( personRoleNote.getId() ) && note.equals( personRoleNote.getNote() ) && session.equals( personRoleNote.getSession() );
	}
}
