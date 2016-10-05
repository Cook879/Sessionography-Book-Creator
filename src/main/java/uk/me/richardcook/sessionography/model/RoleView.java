package uk.me.richardcook.sessionography.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Comparator;


@Entity
@Table( name = "vw_role" )
public class RoleView implements Serializable {

	public static final long serialVersionUID = 1L;

	@Id
	@Column( name = "id" )
	private int id;

	@Column( name = "name" )
	private String name;

	@Column( name = "abbreviation" )
	private String abbreviation;

	@Column( name = "position" )
	private int position;

	// Needed for role group join
	@Column( name = "group" )
	private int group;

	@Column( name = "role_group_name" )
	private String roleGroupName;

	@Column( name = "role_group_position" )
	private int roleGroupPosition;

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

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation( String abbreviation ) {
		this.abbreviation = abbreviation;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition( int position ) {
		this.position = position;
	}

	public String getRoleGroupName() {
		return roleGroupName;
	}

	public void setRoleGroupName( String roleGroupName ) {
		this.roleGroupName = roleGroupName;
	}

	public int getRoleGroupPosition() {
		return roleGroupPosition;
	}

	public void setRoleGroupPosition( int roleGroupPosition ) {
		this.roleGroupPosition = roleGroupPosition;
	}

	// TODO Never used??
	@Override
	public boolean equals( Object obj ) {
		if ( ! obj.getClass().equals( RoleView.class ) )
			return false;
		RoleView role2 = (RoleView) obj;

		return name.equals( role2.getName() );
	}

	public boolean isArranger() {
		return arranger;
	}

	public void setArranger( boolean arranger ) {
		this.arranger = arranger;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup( int group ) {
		this.group = group;
	}

	public static class RoleComparator implements Comparator<RoleView> {
		public int compare( RoleView a, RoleView b ) {
			int groupA = a.getRoleGroupPosition();
			int groupB = b.getRoleGroupPosition();

			if ( groupA > groupB )
				return 1;
			if ( groupA < groupB )
				return - 1;

			int posA = a.getPosition();
			int posB = b.getPosition();

			if ( posA > posB )
				return 1;
			if ( posA < posB )
				return - 1;

			return 0;
		}
	}
}
