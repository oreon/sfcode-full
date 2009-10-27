package org.witchcraft.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Filter;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="name"))
@Name("role")
@Filter(name = "archiveFilterDef")
public class Role extends BusinessEntity {

	private static final long serialVersionUID = -8770292494603643766L;

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
