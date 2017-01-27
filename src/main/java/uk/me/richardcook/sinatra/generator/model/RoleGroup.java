package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table( name = "role_group" )
public class RoleGroup implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private int id;

	@Column( name = "name" )
	private String name;

	@Column( name = "position" )
	private Integer position;

	@Column( name = "arranger" )
	private boolean arranger;

	@Column( name = "[group]" )
	private boolean group;

	@Column( name = "writer" )
	private boolean writer;


	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition( Integer position ) {
		this.position = position;
	}

	public boolean isArranger() {
		return arranger;
	}

	public void setArranger( boolean arranger ) {
		this.arranger = arranger;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( RoleGroup.class ) )
			return false;
		RoleGroup roleGroup = (RoleGroup) obj;

		return id == roleGroup.getId() && name.equals( roleGroup.getName() ) &&
				       position == roleGroup.getPosition() && arranger == roleGroup.isArranger() && writer == roleGroup.isWriter() && group == roleGroup.isGroup();
	}

	public boolean isGroup() {
		return group;
	}

	public void setGroup( boolean group ) {
		this.group = group;
	}

	public boolean isWriter() {
		return writer;
	}

	public void setWriter( boolean writer ) {
		this.writer = writer;
	}
}
