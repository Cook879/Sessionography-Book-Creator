package uk.me.richardcook.sessionography.model;

import javax.persistence.*;

@Entity
@Table( name = "format" )
public class Format {

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
		if ( ! obj.getClass().equals( Format.class ) )
			return false;
		Format format = (Format) obj;

		return id == format.getId() && name.equals( format.getName() );
	}
}
