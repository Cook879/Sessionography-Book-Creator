package uk.me.richardcook.sessionography.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table( name = "song" )
public class SongJoinedBook implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@Column( name = "id" )
	private int id;

	@Column( name = "title" )
	private String title;

	@ManyToOne
	@JoinColumn( name = "source" )
	private SourceView source;

	@ManyToOne
	@JoinColumn( name = "source2" )
	private SourceView source2;

	@Column( name = "year" )
	private String year;

	@Column( name = "notes" )
	private String notes;

	@Column( name = "short_title" )
	private String shortTitle;

	@OneToMany( fetch = FetchType.EAGER, mappedBy = "song" )
	private List<SessionSongSong> sessionSongSongs;

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

	public SourceView getSource() {
		return source;
	}

	public void setSource( SourceView source ) {
		this.source = source;
	}

	public SourceView getSource2() {
		return source2;
	}

	public void setSource2( SourceView source2 ) {
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

	public void writeToBook( BookFile book, boolean showTitle ) {
		if ( showTitle ) {
			book.printBoldText( BookFile.createItalicText( title ) );
			book.printNewLine();
		}

		if ( source != null ) {
			source.writeToBook( book );
			if ( source2 != null ) {
				book.printNewLine();
				source2.writeToBook( book );
			}
			book.printNewLine();
		} else {
			book.printLabel( book.getBookString( "published" ), year );
			book.printNewLine();
		}
	}

	public List<SessionSongSong> getSessionSongSongs() {
		return sessionSongSongs;
	}

	public void setSessionSongSongs( List<SessionSongSong> sessionSongSongs ) {
		this.sessionSongSongs = sessionSongSongs;
	}
}