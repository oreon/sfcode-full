package com.oreon.cerebrum.users.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class AppRoleDto extends BaseEntity {

	protected String name;

	private Set<AppUserDto> appUsersDto = new HashSet<AppUserDto>();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAppUsers(Set<AppUserDto> appUsersDto) {
		this.appUsersDto = appUsersDto;
	}
	public Set<AppUserDto> getAppUsers() {
		return appUsersDto;
	}

}
