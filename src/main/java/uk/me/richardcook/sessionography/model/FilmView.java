package uk.me.richardcook.sessionography.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Class which represents the film entity in the database
 * <p>
 * Film is used to attach to sessions, to say a session was for a certain film
 */
@Entity
@Table( name = "vw_film" )
public class FilmView implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@Column( name = "id" )
	private int id;

	@Column( name = "title" )
	private String title;

	@Column( name = "studio" )
	private String studio;

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

	public String getStudio() {
		return studio;
	}

	public void setStudio( String studio ) {
		this.studio = studio;
	}

	public String getYear() {
		return year;
	}

	public void setYear( String year ) {
		this.year = year;
	}

	public String toBookString() {
		String titleStr = BookFile.createBoldText( BookFile.createItalicText( title ) );
		String details = "(" + studio + ", " + year + ")";
		return titleStr + " " + details;
	}
}
