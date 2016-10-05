package uk.me.richardcook.sessionography.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class which represents the release entity in the database
 */
@Entity
@Table( name = "vw_release" )
public class ReleaseView {

	@Id
	@Column( name = "id" )
	private int id;

	@Column( name = "title" )
	private String title;

	@Column( name = "label" )
	private String label;

	@Column( name = "year" )
	private String year;

	@Column( name = "format" )
	private String format;

	@Column( name = "all" )
	private boolean all;

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

	public String getFormat() {
		return format;
	}

	public void setFormat( String format ) {
		this.format = format;
	}

	public boolean isAll() {
		return all;
	}

	public void setAll( boolean all ) {
		this.all = all;
	}

	public String toBookString( boolean original, BookFile book ) {
		String str = BookFile.createBoldText( BookFile.createItalicText( title ) ) + " (";

		if ( all ) {
			str += label + ", " + book.getBookString( "all-releases" ) + ")";
		} else {
			// Only display the format if it's the original release
			// or the key release isn't on CD
			if ( original || format != null && !format.equals( book.getBookString( "skip-key-format" ) ) ) {
				str += format + ", ";
			}

			if ( year == null && label == null ) {
				str += format + ")";
			} else if ( label == null ) {
				str += year + ")";
			} else if ( year == null ) {
				str += label + ")";
			} else {
				str += label + ", " + year + ")";
			}
		}

		return str;
	}

}
