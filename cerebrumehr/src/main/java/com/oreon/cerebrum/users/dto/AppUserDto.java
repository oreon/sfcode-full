package com.oreon.cerebrum.users.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class AppUserDto extends BaseEntity {

	protected String userName;

	protected String password;

	protected Boolean enabled;

	private Set<AppRoleDto> appRolesDto = new HashSet<AppRoleDto>();

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

	public void setAppRoles(Set<AppRoleDto> appRolesDto) {
		this.appRolesDto = appRolesDto;
	}
	public Set<AppRoleDto> getAppRoles() {
		return appRolesDto;
	}

}
