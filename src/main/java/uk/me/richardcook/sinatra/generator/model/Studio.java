package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;


@Entity
@Table( name = "studio" )
public class Studio {

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
		if ( ! obj.getClass().equals( Studio.class ) )
			return false;
		Studio studio = (Studio) obj;

		return id == studio.getId() && name.equals( studio.getName() );
	}
}
