package uk.me.richardcook.sessionography.model;

import javax.persistence.*;


@Entity
@Table( name = "[with]" )
public class With {

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
		if ( ! obj.getClass().equals( With.class ) )
			return false;
		With with = (With) obj;

		return id == with.getId() && name.equals( with.getName() );
	}
}
