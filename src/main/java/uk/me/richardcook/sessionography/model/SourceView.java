package uk.me.richardcook.sessionography.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table( name = "vw_source" )
public class SourceView implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@Column( name = "id" )
	private int id;

	@Column( name = "name" )
	private String name;

	@Column( name = "studio" )
	private String studio;

	@Column( name = "type" )
	private String type;

	@Column( name = "year" )
	private String year;

	@Column( name = "fromthe" )
	private boolean fromThe;

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

	public String getStudio() {
		return studio;
	}

	public void setStudio( String studio ) {
		this.studio = studio;
	}

	public String getType() {
		return type;
	}

	public void setType( String type ) {
		this.type = type;
	}

	public String getYear() {
		return year;
	}

	public void setYear( String year ) {
		this.year = year;
	}

	public void writeToBook( BookFile book ) {
		String title = BookFile.createBoldText( BookFile.createItalicText( name ) );

		if ( studio != null ) {
			title += " (" + studio + ", " + year + ")";
		} else {
			title += " (" + year + ")";
		}

		if ( fromThe )
			book.printLabel( book.getBookString( "from_the " ) + type, title );
		else
			book.printLabel( type, title );
	}

	public boolean isFromThe() {
		return fromThe;
	}

	public void setFromThe( boolean fromThe ) {
		this.fromThe = fromThe;
	}
}
