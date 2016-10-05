package uk.me.richardcook.sessionography.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "radio" )
public class RadioJoined implements Serializable {

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

	@ManyToOne
	@JoinColumn( name = "sponsor" )
	private Studio sponsor;

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
		if ( ! obj.getClass().equals( RadioJoined.class ) )
			return false;
		RadioJoined radio = (RadioJoined) obj;

		if ( ( sponsor == null && radio.getSponsor() != null ) || ( sponsor != null && ! sponsor.equals( radio.getSponsor() ) ) )
			return false;

		return id == radio.getId() && title.equals( radio.getTitle() ) && year.equals( radio.getYear() )
				       && network.equals( radio.getNetwork() );
	}

	public Studio getSponsor() {
		return sponsor;
	}

	public void setSponsor( Studio sponsor ) {
		this.sponsor = sponsor;
	}

	public Studio getNetwork() {
		return network;
	}

	public void setNetwork( Studio network ) {
		this.network = network;
	}
}
