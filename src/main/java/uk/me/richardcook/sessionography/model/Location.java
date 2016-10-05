package uk.me.richardcook.sessionography.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class which represents the location entity in the database
 * <p>
 * Sessions occur at locations.
 */
@Entity
@Table( name = "location" )
public class Location implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "name" )
	private String name;

	@Column( name = "at" )
	private boolean at;

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

	public boolean isAt() {
		return at;
	}

	public void setAt( boolean at ) {
		this.at = at;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( Location.class ) )
			return false;
		Location location = (Location) obj;

		return id == location.getId() && name.equals( location.getName() ) && at == location.isAt();
	}

	public void writeToBook( BookFile book ) {
		if ( at )
			book.printLabel( book.getBookString( "at" ), name );
		else
			book.printLabel( book.getBookString( "in" ), name );
	}

}
