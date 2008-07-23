package com.cc.civlit.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.cc.civlit.domain.Firm;
import com.cc.civlit.service.FirmService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

import com.cc.civlit.domain.FirmAdministrator;

/**
 * This is generated code - to edit code or override methods use - Firm class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class FirmBackingBeanBase extends BaseBackingBean<Firm> {

	protected Firm firm = new Firm();

	protected FirmService firmService;

	private List<FirmAdministrator> listFirmAdministrators = new ArrayList<FirmAdministrator>();

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public Firm getFirm() {
		return firm;
	}

	public void setFirm(Firm firm) {
		this.firm = firm;
	}

	public void setFirmService(FirmService firmService) {
		this.firmService = firmService;
	}

	public FirmService getFirmService() {
		return this.firmService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Firm> getBaseService() {
		return firmService;
	}

	public Firm getEntity() {
		return getFirm();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		firm = new Firm();
		resetRanges();

		listFirmAdministrators.clear();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		firm = firmService.load(id);

	}

	@Override
	public String update() {

		addFirmAdministratorsToFirm();

		return super.update();
	}

	public List<FirmAdministrator> getListFirmAdministrators() {
		if (listFirmAdministrators.isEmpty())
			loadFirmAdministrators();

		return listFirmAdministrators;
	}

	public void setListFirmAdministrators(
			List<FirmAdministrator> listFirmAdministrators) {
		this.listFirmAdministrators = listFirmAdministrators;
	}

	private void loadFirmAdministrators() {
		listFirmAdministrators.clear();
		if (firm != null) {
			listFirmAdministrators.addAll(firm.getFirmAdministrator());
		}
		int sizeOfExistingElements = listFirmAdministrators.size();
		// add a few spare rows - lets say parent has 3 children and we need to
		// show 5 rows - then add 2 rows with 2 new parents
		for (int i = 0; i < INITIAL_RECORDS - sizeOfExistingElements; i++) {
			listFirmAdministrators.add(new FirmAdministrator());
		}
	}

	private void addFirmAdministratorsToFirm() {
		firm.getFirmAdministrator().clear();
		List<FirmAdministrator> listValidFirmAdministrators = new ArrayList<FirmAdministrator>();

		for (FirmAdministrator firmAdministrator : listFirmAdministrators) {

			firmAdministrator.setFirm(firm);
			listValidFirmAdministrators.add(firmAdministrator);

		}

		firm.getFirmAdministrator().addAll(listValidFirmAdministrators);
	}

	/**
	 * @param actionEvent
	 */
	public void addNewFirmAdministratorRow(ActionEvent actionEvent) {
		listFirmAdministrators.add(new FirmAdministrator());
	}

	/**
	 * @param actionEvent
	 */
	public void deleteFirmAdministratorRow(ActionEvent actionEvent) {
		String rowIndex = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get(
						"deleteRowIndex");

		int index = Integer.parseInt(rowIndex);
		FirmAdministrator firmAdministrator = listFirmAdministrators.get(index);
		listFirmAdministrators.remove(index);

		/*
			TaskService taskService = (TaskService) BeanHelper
					.getBean("taskService");

			if (task.getId() != null && task.getId() > 0) {
				taskService.delete(task);
			}*/
	}

}
