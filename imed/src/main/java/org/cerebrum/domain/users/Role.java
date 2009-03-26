package org.cerebrum.domain.users;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

@Entity
@Table(name = "role")
@Name("role")
@Filter(name = "archiveFilterDef")
public class Role extends BusinessEntity {

	@Unique(entityName = "org.cerebrum.domain.users.Role", fieldName = "name")
	@NotNull
	@Length(min = 2, max = 50)
	protected String name;

	//user->roles ->Role->Role->Role

	@ManyToMany(mappedBy = "roles")
	private Set<User> user = new HashSet<User>();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	public Set<User> getUser() {
		return user;
	}

	@Transient
	public String getDisplayName() {
		return name + "";
	}

}
