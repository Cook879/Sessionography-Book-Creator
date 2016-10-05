package uk.me.richardcook.sessionography.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class which represents the film entity in the database
 * <p>
 * Film is used to attach to sessions, to say a session was for a certain film
 */
@Entity
@Table( name = "film" )
public class FilmJoined implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "title" )
	private String title;

	@ManyToOne
	@JoinColumn( name = "studio" )
	private Studio studio;

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

	public Studio getStudio() {
		return studio;
	}

	public void setStudio( Studio studio ) {
		this.studio = studio;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( FilmJoined.class ) )
			return false;
		FilmJoined film = (FilmJoined) obj;

		return id == film.getId() && title.equals( film.getTitle() ) && year.equals( film.getYear() )
				       && studio.equals( film.getStudio() );
	}
}
