package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.kgauge.domain.GrantedRole;
import com.oreon.kgauge.service.GrantedRoleService;

import java.util.Date;
import java.util.List;
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

	public void setGrantedRoleService(GrantedRoleService grantedRoleService) {
		this.grantedRoleService = grantedRoleService;
	}

	public GrantedRole getGrantedRole() {
		return grantedRole;
	}

	public void set(GrantedRole grantedRole) {
		this.grantedRole = grantedRole;
	}

	@SuppressWarnings("unchecked")
	public BaseService<GrantedRole> getBaseService() {
		return grantedRoleService;
	}

	public GrantedRole getEntity() {
		return getGrantedRole();
	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	/** This action Listener Method is called when a row is clicked in the dataTable
	 *  
	 * @param event contains the database id of the row being selected 
	 */
	public void selectEntity(ActionEvent actionEvent) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		String idStr = (String) ctx.getExternalContext()
				.getRequestParameterMap().get("id");
		long id = Long.parseLong(idStr);
		grantedRole = grantedRoleService.load(id);
		if (actionEvent.getComponent().getId() == "deleteId") {
			getBaseService().delete(grantedRole);
		}
		/*
		UIParameter component = (UIParameter) actionEvent.getComponent().findComponent("editId");
		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());
		 */
	}

}
