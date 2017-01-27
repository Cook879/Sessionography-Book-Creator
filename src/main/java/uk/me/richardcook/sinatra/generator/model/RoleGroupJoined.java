package uk.me.richardcook.sinatra.generator.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table( name = "role_group" )
public class RoleGroupJoined implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@Column( name = "id" )
	private int id;

	@Column( name = "name" )
	private String name;

	@Column( name = "position" )
	private int position;

	@OneToMany( fetch = FetchType.EAGER, mappedBy = "group" )
	@OrderBy( value = "position" )
	private List<RoleView> roles;

	@Column( name = "arranger" )
	private boolean arranger;

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

	public int getPosition() {
		return position;
	}

	public void setPosition( int position ) {
		this.position = position;
	}

	public boolean isArranger() {
		return arranger;
	}

	public void setArranger( boolean arranger ) {
		this.arranger = arranger;
	}

	public List<RoleView> getRoles() {
		return roles;
	}

	public void setRoles( List<RoleView> roles ) {
		this.roles = roles;
	}
}