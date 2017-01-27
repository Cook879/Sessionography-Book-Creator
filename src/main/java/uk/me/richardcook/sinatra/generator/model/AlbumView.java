package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class which represents the album entity in the database
 * <p>
 * Album is used to attach to sessions, to say a session was for a certain album
 */
@Entity
@Table( name = "vw_album" )
public class AlbumView {

	@Id
	@Column( name = "id" )
	private int id;

	@Column( name = "title" )
	private String title;

	@Column( name = "label" )
	private String label;

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

	public String getLabel() {
		return label;
	}

	public void setLabel( String label ) {
		this.label = label;
	}

	public String getYear() {
		return year;
	}

	public void setYear( String year ) {
		this.year = year;
	}

	public String toBookString() {
		String titleStr = BookFile.createBoldText( BookFile.createItalicText( title ) );
		String details = "(" + label + ", " + year + ")";
		return titleStr + " " + details;
	}
}
