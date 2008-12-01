
/**
 * This is generated code - to edit code or override methods use - User class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.domain.dto;

import com.oreon.kgauge.domain.*;
import java.util.Date;

public class UserDto {

	private String username;

	private String password;

	private boolean enabled;

	private java.util.Set<GrantedRoleDto> grantedRoles = new java.util.HashSet<GrantedRoleDto>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void addGrantedRole(GrantedRoleDto grantedRoles) {
		//checkMaximumGrantedRoles();
		grantedRoles.setUser(this);
		this.grantedRoles.add(grantedRoles);
	}

	public void removeGrantedRole(GrantedRoleDto grantedRoles) {
		this.grantedRoles.remove(grantedRoles);
	}

	public java.util.Set<GrantedRoleDto> getGrantedRoles() {
		return this.grantedRoles;
	}

	public void setGrantedRoles(java.util.Set<GrantedRoleDto> grantedRoles) {
		this.grantedRoles = grantedRoles;
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	public int getGrantedRolesCount() {
		return this.grantedRoles.size();
	}

	/*
	public void checkMaximumGrantedRoles(){
		// if(grantedRoles.size() > Constants.size())
		// 		throw new BusinessException ("msg.tooMany." + grantedRoles );
	}*/

}
