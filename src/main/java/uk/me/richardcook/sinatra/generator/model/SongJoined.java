package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "song" )
public class SongJoined implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "title" )
	private String title;

	@ManyToOne
	@JoinColumn( name = "source" )
	private Source source;

	@ManyToOne
	@JoinColumn( name = "source2" )
	private Source source2;

	@Column( name = "year" )
	private String year;

	@Column( name = "notes" )
	private String notes;

	@Column( name = "short_title" )
	private String shortTitle;

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

	public Source getSource() {
		return source;
	}

	public void setSource( Source source ) {
		this.source = source;
	}

	public Source getSource2() {
		return source2;
	}

	public void setSource2( Source source2 ) {
		this.source2 = source2;
	}

	public String getYear() {
		return year;
	}

	public void setYear( String year ) {
		this.year = year;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes( String notes ) {
		this.notes = notes;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle( String shortTitle ) {
		this.shortTitle = shortTitle;
	}


	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( SongJoined.class ) )
			return false;
		SongJoined song = (SongJoined) obj;

		if ( id != song.getId() )
			return false;

		if ( ! title.equals( song.getTitle() ) )
			return false;

		if ( ( source == null && song.getSource() != null ) || ( source != null && ! source.equals( song.getSource() ) ) )
			return false;

		if ( ! year.equals( song.getYear() ) )
			return false;

		if ( ( notes == null && song.getNotes() != null ) || ( notes != null && ! notes.equals( song.getNotes() ) ) )
			return false;

		if ( ( source2 == null && song.getSource2() != null ) || ( source2 != null && ! source2.equals( song.getSource2() ) ) )
			return false;

		if ( ! shortTitle.equals( song.getShortTitle() ) )
			return false;

		return true;
	}

}

