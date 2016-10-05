package uk.me.richardcook.sessionography.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "vw_television" )
public class TelevisionView implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@Column( name = "id" )
	private int id;

	@Column( name = "title" )
	private String title;

	@Column( name = "network" )
	private String network;

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

	public String getNetwork() {
		return network;
	}

	public void setNetwork( String network ) {
		this.network = network;
	}

	public String getYear() {
		return year;
	}

	public void setYear( String year ) {
		this.year = year;
	}

	public String toBookString() {
		String titleStr = BookFile.createBoldText( BookFile.createItalicText( title ) );
		String details = "(" + network + ", " + year + ")";
		return titleStr + " " + details;
	}
}
