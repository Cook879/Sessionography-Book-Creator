package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "radio" )
public class Radio implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "title" )
	private String title;

	@Column( name = "network" )
	private Integer network;

	@Column( name = "sponsor" )
	private Integer sponsor;

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
		if ( ! obj.getClass().equals( Radio.class ) )
			return false;
		Radio radio = (Radio) obj;

		if ( ( sponsor == null && radio.getSponsor() != null ) || ( sponsor != null && ! sponsor.equals( radio.getSponsor() ) ) )
			return false;

		return id == radio.getId() && title.equals( radio.getTitle() ) && year.equals( radio.getYear() )
				       && network.equals( radio.getNetwork() );
	}

	public Integer getNetwork() {
		return network;
	}

	public void setNetwork( Integer network ) {
		this.network = network;
	}

	public Integer getSponsor() {
		return sponsor;
	}

	public void setSponsor( Integer sponsor ) {
		this.sponsor = sponsor;
	}
}
