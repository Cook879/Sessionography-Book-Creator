package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "role" )
public class Role implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "name" )
	private String name;

	@Column( name = "abbreviation" )
	private String abbreviation;

	@Column( name = "position" )
	private Integer position;

	@Column( name = "[group]" )
	private Integer group;

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation( String abbreviation ) {
		this.abbreviation = abbreviation;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition( Integer position ) {
		this.position = position;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( Role.class ) )
			return false;
		Role role2 = (Role) obj;

		return id == role2.getId() && name.equals( role2.getName() ) && abbreviation.equals( role2.getAbbreviation() )
				       && position.equals( role2.getPosition() ) && group.equals( role2.getGroup() );
	}


	public Integer getGroup() {
		return group;
	}

	public void setGroup( Integer group ) {
		this.group = group;
	}
}
