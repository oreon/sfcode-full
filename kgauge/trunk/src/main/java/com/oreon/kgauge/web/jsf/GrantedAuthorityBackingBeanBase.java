package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.kgauge.domain.GrantedAuthority;
import com.oreon.kgauge.service.GrantedAuthorityService;

import java.util.Date;
import java.util.List;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - GrantedAuthority class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class GrantedAuthorityBackingBeanBase
		extends
			BaseBackingBean<GrantedAuthority> {

	private GrantedAuthority grantedAuthority = new GrantedAuthority();

	private GrantedAuthorityService grantedAuthorityService;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public void setGrantedAuthorityService(
			GrantedAuthorityService grantedAuthorityService) {
		this.grantedAuthorityService = grantedAuthorityService;
	}

	public GrantedAuthority getGrantedAuthority() {
		return grantedAuthority;
	}

	public void set(GrantedAuthority grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}

	@SuppressWarnings("unchecked")
	public BaseService<GrantedAuthority> getBaseService() {
		return grantedAuthorityService;
	}

	public GrantedAuthority getEntity() {
		return getGrantedAuthority();
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
		grantedAuthority = grantedAuthorityService.load(id);
		if (actionEvent.getComponent().getId() == "deleteId") {
			getBaseService().delete(grantedAuthority);
		}
		/*
		UIParameter component = (UIParameter) actionEvent.getComponent().findComponent("editId");
		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());
		 */
	}

}
