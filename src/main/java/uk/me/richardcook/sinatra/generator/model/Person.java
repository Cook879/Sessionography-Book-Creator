package uk.me.richardcook.sinatra.generator.model;

import uk.me.richardcook.sinatra.generator.service.BookPersonRoleService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Class which represents the person entity in the database
 * <p>
 * People are important
 */
@Entity
@Table( name = "person" )
public class Person implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	public int id;

	@Column( name = "first_name" )
	private String firstName;

	@Column( name = "last_name" )
	private String lastName;

	// Temporary variable, used for the personnel generation
	// Maps roles to the session song positions the Person performed them on
	@Transient
	private Map<RoleView, Set<Integer>> roleSessionSongs = new HashMap<RoleView, Set<Integer>>();

	// Note of the person for the current session
	@Transient
	private Integer note;

	// The current session id
	@Transient
	private Integer currSession;

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		if ( firstName == null )
			return lastName;

		return firstName + " " + lastName;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( Person.class ) )
			return false;
		Person person1 = (Person) obj;
		// First let's check first names - null complicates matters
		String person1FirstName = person1.getFirstName();

		if ( ( firstName == null && person1FirstName != null ) || ( firstName != null && ! firstName.equals( person1FirstName ) ) )
			return false;

		String person1LastName = person1.getLastName();
		if ( ( lastName == null && person1LastName != null ) || ( lastName != null && ! lastName.equals( person1LastName ) ) )
			return false;
		return true;
	}

	public void addSessionSong( RoleView role, Integer position ) {
		if ( roleSessionSongs.containsKey( role ) ) {
			roleSessionSongs.get( role ).add( position );
		} else {
			Set<Integer> list = new HashSet<Integer>();
			list.add( position );
			roleSessionSongs.put( role, list );
		}
	}

	public void resetRoleSessionSongs() {
		roleSessionSongs = new HashMap<RoleView, Set<Integer>>();
	}

	public Integer getNote() {
		return note;
	}

	public void setNote( Integer note ) {
		this.note = note;
	}

	public Integer getCurrSession() {
		return currSession;
	}

	public void setCurrSession( Integer currSession ) {
		this.currSession = currSession;
	}

	/**
	 * Converts personnel to a string to put in the book
	 */
	public String toPersonnelString( RoleView role, int positionsCount, Integer type ) {
		String notes = checkNote();

		if ( type == BookPersonRoleService.PERSON_ROLE_SESSION_SONG ) {
			String exceptions = createExceptions( role, positionsCount );

			if ( exceptions != null ) {
				if ( notes != null )
					return toString() + notes + " " + exceptions;
				return toString() + " " + exceptions;
			}
		}

		if ( notes != null )
			return toString() + notes;

		return toString();
	}

	/**
	 * Compares the amount of songs in the session with the number they have played the role on
	 * If there's a difference, then exceptions are created and added to the string
	 * Really, it's create the non-exceptions, but not as good a name
	 */
	private String createExceptions( RoleView role, int positionsCount ) {
		Set<Integer> sessionSongsSet = roleSessionSongs.get( role );
		if ( positionsCount == sessionSongsSet.size() ) {
			return null;
		}

		List<Integer> sessionSongs = Collections.list( Collections.enumeration( sessionSongsSet ) );

		boolean first = true;
		String exceptions = "(";

		// Positions to skip because we already cover them in a continuous sessionSong
		Set<Integer> forSkip = new HashSet<Integer>();

		for ( int i = 0; i < sessionSongs.size(); i++ ) {
			if ( ! forSkip.contains( i ) ) {
				boolean stop = false;
				String result = BookFile.getCharForNumber( sessionSongs.get( i ) );

				// Before we add this result, let's check if we have continuous sessionSongs.
				int j = i;
				int k = j + 1;
				while ( ! stop ) {
					if ( k < sessionSongs.size() && sessionSongs.get( k ) == sessionSongs.get( j ) + 1 ) {
						// We got a match
						result = BookFile.getCharForNumber( sessionSongs.get( i ) ) + "-" + BookFile.getCharForNumber( sessionSongs.get( k ) );
						forSkip.add( j );
						forSkip.add( k );
						j = k;
						k = j + 1;
					} else {
						stop = true;
					}
				}
				if ( ! first ) {
					exceptions += ";";
				} else {
					first = false;
				}
				exceptions += result;
			}
		}
		exceptions += ")";
		return BookFile.createItalicText( exceptions );
	}

	private String checkNote() {
		if ( note == null )
			return null;

		return BookFile.createSuperScriptText( Integer.toString( note ) );
	}

	public static class PersonSort implements Comparator<Person> {
		public int compare( Person a, Person b ) {
			String aLastName = a.getLastName();
			String bLastName = b.getLastName();

			if ( ! aLastName.equals( bLastName ) )
				return aLastName.compareTo( bLastName );

			String aFirstName = a.getFirstName();
			String bFirstName = b.getFirstName();

			return aFirstName.compareTo( bFirstName );
		}
	}
}
