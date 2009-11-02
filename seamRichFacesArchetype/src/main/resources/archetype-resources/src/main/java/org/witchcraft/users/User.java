package org.witchcraft.users;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(uniqueConstraints=@UniqueConstraint(columnNames="username"))
@Name("user")
@Filter(name = "archiveFilterDef")
public class User extends BusinessEntity {

	private static final long serialVersionUID = -2034874428471316479L;

	@NotNull
	@Length(min = 2, max = 50)
	protected String userName;

	@NotNull
	protected String password;

	protected Boolean enabled;

	//roles->user ->User->Role->Role

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_ID", unique = true), inverseJoinColumns = @JoinColumn(name = "roles_ID", unique = true))
	private Set<Role> roles = new HashSet<Role>();

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	@Transient
	public String getDisplayName() {
		return userName + "";
	}

}
