package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;

@Entity
@Table( name = "label" )
public class Label {

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
		if ( ! obj.getClass().equals( Label.class ) )
			return false;
		Label label = (Label) obj;

		return id == label.getId() && name.equals( label.getName() );
	}

}
