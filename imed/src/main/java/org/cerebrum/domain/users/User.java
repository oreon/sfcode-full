package org.cerebrum.domain.users;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.*;

@Entity
@Table(name = "user")
@Name("user")
public class User extends BusinessEntity {

	//@Unique

	@NotNull
	@Length(min = 2, max = 50)
	protected String userName;

	@NotNull
	protected String password;

	protected Boolean enabled;

	@OneToMany(mappedBy = "", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "User_ID", nullable = true)
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
