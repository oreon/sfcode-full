package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.kgauge.domain.Authority;
import com.oreon.kgauge.service.AuthorityService;

import java.util.Date;
import java.util.List;
import org.witchcraft.model.support.Range;

public class AuthorityBackingBean extends BaseBackingBean<Authority> {

	private Authority authority = new Authority();

	private AuthorityService authorityService;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void set(Authority authority) {
		this.authority = authority;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Authority> getBaseService() {
		return authorityService;
	}

	public Authority getEntity() {
		return getAuthority();
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
		authority = authorityService.load(id);
		if (actionEvent.getComponent().getId() == "deleteId") {
			getBaseService().delete(authority);
		}
		/*
		UIParameter component = (UIParameter) actionEvent.getComponent().findComponent("editId");
		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());
		 */
	}

}
