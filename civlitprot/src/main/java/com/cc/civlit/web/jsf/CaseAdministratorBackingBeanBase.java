package com.cc.civlit.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.cc.civlit.domain.CaseAdministrator;
import com.cc.civlit.service.CaseAdministratorService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - CaseAdministrator class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class CaseAdministratorBackingBeanBase
		extends
			BaseBackingBean<CaseAdministrator> {

	protected CaseAdministrator caseAdministrator = new CaseAdministrator();

	protected CaseAdministratorService caseAdministratorService;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	private Range<Date> rangeDateOfBirth = new Range<Date>("dateOfBirth");

	public Range<Date> getRangeDateOfBirth() {
		return rangeDateOfBirth;
	}

	public void setRangeDateOfBirth(Range<Date> rangeDateOfBirth) {
		this.rangeDateOfBirth = rangeDateOfBirth;
	}

	public CaseAdministrator getCaseAdministrator() {
		return caseAdministrator;
	}

	public void setCaseAdministrator(CaseAdministrator caseAdministrator) {
		this.caseAdministrator = caseAdministrator;
	}

	public void setCaseAdministratorService(
			CaseAdministratorService caseAdministratorService) {
		this.caseAdministratorService = caseAdministratorService;
	}

	public CaseAdministratorService getCaseAdministratorService() {
		return this.caseAdministratorService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<CaseAdministrator> getBaseService() {
		return caseAdministratorService;
	}

	public CaseAdministrator getEntity() {
		return getCaseAdministrator();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		caseAdministrator = new CaseAdministrator();
		resetRanges();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeDateOfBirth);

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		caseAdministrator = caseAdministratorService.load(id);

	}

}
