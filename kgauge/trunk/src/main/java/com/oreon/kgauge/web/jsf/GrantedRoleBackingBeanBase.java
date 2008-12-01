package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import java.util.Set;
import org.apache.commons.collections.ListUtils;

import com.oreon.kgauge.domain.GrantedRole;
import com.oreon.kgauge.service.GrantedRoleService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - GrantedRole class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class GrantedRoleBackingBeanBase extends BaseBackingBean<GrantedRole> {

	protected GrantedRole grantedRole = new GrantedRole();

	protected GrantedRoleService grantedRoleService;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public GrantedRole getGrantedRole() {
		return grantedRole;
	}

	public void setGrantedRole(GrantedRole grantedRole) {
		this.grantedRole = grantedRole;
	}

	public void setGrantedRoleService(GrantedRoleService grantedRoleService) {
		this.grantedRoleService = grantedRoleService;
	}

	public GrantedRoleService getGrantedRoleService() {
		return this.grantedRoleService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<GrantedRole> getBaseService() {
		return grantedRoleService;
	}

	public GrantedRole getEntity() {
		return getGrantedRole();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		grantedRole = new GrantedRole();
		resetRanges();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		if (id != 0)
			grantedRole = grantedRoleService.load(id);

	}

	@Override
	public String update() {

		return super.update();
	}

}
