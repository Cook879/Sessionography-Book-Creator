package uk.me.richardcook.sessionography.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "book_string" )
public class BookString {

	@Id
	@Column( name = "id" )
	private Integer id;

	@Column( name = "name" )
	private String name;

	@Column( name = "value" )
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue( String value ) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
		this.id = id;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( BookString.class ) )
			return false;
		BookString bookString = (BookString) obj;

		return id.equals( bookString.getId() ) && name.equals( bookString.getName() ) && value.equals( bookString.getValue() );
	}
}
