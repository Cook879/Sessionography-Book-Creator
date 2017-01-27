package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;


@Entity
@Table( name = "source_type" )
public class SourceType {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "name" )
	private String name;

	@Column( name = "fromthe" )
	private boolean fromThe;

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( SourceType.class ) )
			return false;
		SourceType sourceType = (SourceType) obj;

		return id == sourceType.getId() && name.equals( sourceType.getName() ) && fromThe == sourceType.isFromThe();
	}

	public boolean isFromThe() {
		return fromThe;
	}

	public void setFromThe( boolean fromThe ) {
		this.fromThe = fromThe;
	}
}
