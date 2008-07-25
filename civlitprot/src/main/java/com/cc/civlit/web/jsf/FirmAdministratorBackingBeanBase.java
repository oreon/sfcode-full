package com.cc.civlit.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.cc.civlit.domain.FirmAdministrator;
import com.cc.civlit.service.FirmAdministratorService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - FirmAdministrator class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class FirmAdministratorBackingBeanBase
		extends
			BaseBackingBean<FirmAdministrator> {

	protected FirmAdministrator firmAdministrator = new FirmAdministrator();

	protected FirmAdministratorService firmAdministratorService;

	//This field is used to store the currently logged in FirmAdministrator
	protected FirmAdministrator loggedinFirmAdministrator;

	private String repeatPassword;

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatpassword) {
		this.repeatPassword = repeatpassword;
	}

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

	public FirmAdministrator getFirmAdministrator() {
		return firmAdministrator;
	}

	public void setFirmAdministrator(FirmAdministrator firmAdministrator) {
		this.firmAdministrator = firmAdministrator;
	}

	public void setFirmAdministratorService(
			FirmAdministratorService firmAdministratorService) {
		this.firmAdministratorService = firmAdministratorService;
	}

	public FirmAdministratorService getFirmAdministratorService() {
		return this.firmAdministratorService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<FirmAdministrator> getBaseService() {
		return firmAdministratorService;
	}

	public FirmAdministrator getEntity() {
		return getFirmAdministrator();
	}

	public FirmAdministrator getLoggedinFirmAdministrator() {
		if (loggedinFirmAdministrator == null) {
			loggedinFirmAdministrator = getFirmAdministratorService()
					.findByUsername(getAuthenticationController().getUsername());
		}
		return loggedinFirmAdministrator;
	}

	public void setLoggedinFirmAdministrator(
			FirmAdministrator loggedinFirmAdministrator) {
		this.loggedinFirmAdministrator = loggedinFirmAdministrator;
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		firmAdministrator = new FirmAdministrator();
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
		firmAdministrator = firmAdministratorService.load(id);

		repeatPassword = firmAdministrator.getUser().getPassword();

	}

}
