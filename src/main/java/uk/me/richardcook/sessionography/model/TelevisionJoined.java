package uk.me.richardcook.sessionography.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "television" )
public class TelevisionJoined implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "title" )
	private String title;

	@ManyToOne
	@JoinColumn( name = "network" )
	private Studio network;

	@Column( name = "year" )
	private String year;

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle( String title ) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear( String year ) {
		this.year = year;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( TelevisionJoined.class ) )
			return false;
		TelevisionJoined television = (TelevisionJoined) obj;

		return id == television.getId() && title.equals( television.getTitle() ) && year.equals( television.getYear() )
				       && network.equals( television.getNetwork() );
	}

	public Studio getNetwork() {
		return network;
	}

	public void setNetwork( Studio network ) {
		this.network = network;
	}
}
