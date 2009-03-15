package org.wcdemo.xstories;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.jboss.seam.annotations.Name;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.base.entity.Unique;

@MappedSuperclass
public class ApplicationUser extends BusinessEntity {

	@Unique(entityName = "org.wcdemo.xstories.TeamMember", fieldName = "userName")
	protected String userName;

	protected String password;

	protected Boolean enabled = true;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "applicationRole_id", nullable = true)
	protected ApplicationRole applicationRole;

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

	public void setApplicationRole(ApplicationRole applicationRole) {
		this.applicationRole = applicationRole;
	}

	public ApplicationRole getApplicationRole() {
		return applicationRole;
	}

}
