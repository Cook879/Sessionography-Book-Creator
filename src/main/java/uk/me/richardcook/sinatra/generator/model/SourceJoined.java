package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "source" )
public class SourceJoined implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "name" )
	private String name;

	@ManyToOne
	@JoinColumn( name = "studio" )
	private Studio studio;

	@Column( name = "type" )
	private Integer type;

	@Column( name = "year" )
	private String year;

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

	public Studio getStudio() {
		return studio;
	}

	public void setStudio( Studio studio ) {
		this.studio = studio;
	}

	public Integer getType() {
		return type;
	}

	public void setType( Integer type ) {
		this.type = type;
	}

	public String getYear() {
		return year;
	}

	public void setYear( String year ) {
		this.year = year;
	}


	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( Session.class ) )
			return false;
		SourceJoined source = (SourceJoined) obj;

		if ( id != source.getId() )
			return false;

		if ( ! name.equals( source.getName() ) )
			return false;

		if ( ! type.equals( source.getType() ) )
			return false;

		if ( ! year.equals( source.getYear() ) )
			return false;

		if ( ( studio == null && source.getStudio() != null ) || ( studio != null && ! studio.equals( source.getStudio() ) ) )
			return false;

		return true;
	}
}
