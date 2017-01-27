package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class which represents the for_other entity in the database
 * <p>
 * Designed to attach to sessions when an album, label, film, TV show or radio show is not appropriate
 */
@Entity
@Table( name = "for_other" )
public class ForOther implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "description" )
	private String description;

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription( String description ) {
		this.description = description;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( ForOther.class ) )
			return false;
		ForOther forOther = (ForOther) obj;

		return id == forOther.getId() && description.equals( forOther.getDescription() );
	}

}
