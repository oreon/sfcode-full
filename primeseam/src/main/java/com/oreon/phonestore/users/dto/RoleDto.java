package com.oreon.phonestore.users.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.FileAttachment;
import java.math.BigDecimal;

public class RoleDto extends BaseEntity {

	protected String name;

	private Set<UserDto> usersDto = new HashSet<UserDto>();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setUsers(Set<UserDto> usersDto) {
		this.usersDto = usersDto;
	}
	public Set<UserDto> getUsers() {
		return usersDto;
	}

}
