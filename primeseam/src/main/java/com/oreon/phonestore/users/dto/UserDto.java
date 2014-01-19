package com.oreon.phonestore.users.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class UserDto extends BaseEntity {

	protected String userName;

	protected String password;

	protected Boolean enabled;

	private Set<RoleDto> rolesDto = new HashSet<RoleDto>();

	protected String email;

	protected Date lastLogin;

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

	public void setRoles(Set<RoleDto> rolesDto) {
		this.rolesDto = rolesDto;
	}
	public Set<RoleDto> getRoles() {
		return rolesDto;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

}
