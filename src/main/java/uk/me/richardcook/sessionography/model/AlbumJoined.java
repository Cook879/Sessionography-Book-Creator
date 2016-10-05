package uk.me.richardcook.sessionography.model;

import javax.persistence.*;

/**
 * Class which represents the album entity in the database
 * <p>
 * Album is used to attach to sessions, to say a session was for a certain album
 * Albums can also be attached to releases
 */
@Entity
@Table( name = "album" )
public class AlbumJoined {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "title" )
	private String title;

	@ManyToOne
	@JoinColumn( name = "label" )
	private Label label;

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

	public Label getLabel() {
		return label;
	}

	public void setLabel( Label label ) {
		this.label = label;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( AlbumJoined.class ) )
			return false;
		AlbumJoined album = (AlbumJoined) obj;

		return id == album.getId() && title.equals( album.getTitle() ) && year.equals( album.getYear() )
				       && label.equals( album.getLabel() );
	}
}
