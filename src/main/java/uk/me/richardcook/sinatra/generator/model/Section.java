package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class which represents the section entity in the database
 * <p>
 * A section represents a chapter in the book, and consists of sessions
 */
@Entity
@Table( name = "section" )
public class Section implements Serializable {

	public static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "title" )
	private String title;

	@Column( name = "description" )
	private String description;

	@Column( name = "position" )
	private Integer position;

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

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition( Integer position ) {
		this.position = position;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( Section.class ) )
			return false;
		Section section = (Section) obj;

		return id == section.getId() && title.equals( section.getTitle() ) && description.equals( section.getDescription() )
				       && position == section.getPosition();
	}
}
