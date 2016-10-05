package uk.me.richardcook.sessionography.model;

import javax.persistence.*;

@Entity
@Table( name = "session_type" )
public class SessionType {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "name" )
	private String name;

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( SessionType.class ) )
			return false;
		SessionType sessionType = (SessionType) obj;

		return id == sessionType.getId() && name.equals( sessionType.getName() );
	}
}
