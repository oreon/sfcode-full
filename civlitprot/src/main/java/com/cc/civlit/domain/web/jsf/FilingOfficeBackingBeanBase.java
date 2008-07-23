package com.cc.civlit.domain.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.cc.civlit.domain.courtdivisions.FilingOffice;
import com.cc.civlit.domain.service.FilingOfficeService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

import com.cc.civlit.domain.courtdivisions.Divsion;

/**
 * This is generated code - to edit code or override methods use - FilingOffice class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class FilingOfficeBackingBeanBase extends BaseBackingBean<FilingOffice> {

	protected FilingOffice filingOffice = new FilingOffice();

	protected FilingOfficeService filingOfficeService;

	private List<Divsion> listDivsions = new ArrayList<Divsion>();

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public FilingOffice getFilingOffice() {
		return filingOffice;
	}

	public void setFilingOffice(FilingOffice filingOffice) {
		this.filingOffice = filingOffice;
	}

	public void setFilingOfficeService(FilingOfficeService filingOfficeService) {
		this.filingOfficeService = filingOfficeService;
	}

	public FilingOfficeService getFilingOfficeService() {
		return this.filingOfficeService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<FilingOffice> getBaseService() {
		return filingOfficeService;
	}

	public FilingOffice getEntity() {
		return getFilingOffice();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		filingOffice = new FilingOffice();
		resetRanges();

		listDivsions.clear();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		filingOffice = filingOfficeService.load(id);

	}

	@Override
	public String update() {

		addDivsionsToFilingOffice();

		return super.update();
	}

	public List<Divsion> getListDivsions() {
		if (listDivsions.isEmpty())
			loadDivsions();

		return listDivsions;
	}

	public void setListDivsions(List<Divsion> listDivsions) {
		this.listDivsions = listDivsions;
	}

	private void loadDivsions() {
		listDivsions.clear();
		if (filingOffice != null) {
			listDivsions.addAll(filingOffice.getDivsion());
		}
		int sizeOfExistingElements = listDivsions.size();
		// add a few spare rows - lets say parent has 3 children and we need to
		// show 5 rows - then add 2 rows with 2 new parents
		for (int i = 0; i < INITIAL_RECORDS - sizeOfExistingElements; i++) {
			listDivsions.add(new Divsion());
		}
	}

	private void addDivsionsToFilingOffice() {
		filingOffice.getDivsion().clear();
		List<Divsion> listValidDivsions = new ArrayList<Divsion>();

		for (Divsion divsion : listDivsions) {

			divsion.setFilingOffice(filingOffice);
			listValidDivsions.add(divsion);

		}

		filingOffice.getDivsion().addAll(listValidDivsions);
	}

	/**
	 * @param actionEvent
	 */
	public void addNewDivsionRow(ActionEvent actionEvent) {
		listDivsions.add(new Divsion());
	}

	/**
	 * @param actionEvent
	 */
	public void deleteDivsionRow(ActionEvent actionEvent) {
		String rowIndex = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get(
						"deleteRowIndex");

		int index = Integer.parseInt(rowIndex);
		Divsion divsion = listDivsions.get(index);
		listDivsions.remove(index);

		/*
			TaskService taskService = (TaskService) BeanHelper
					.getBean("taskService");

			if (task.getId() != null && task.getId() > 0) {
				taskService.delete(task);
			}*/
	}

}
