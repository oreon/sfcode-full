package com.cc.civlit.domain.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.cc.civlit.domain.courtdivisions.LevelOfCourt;
import com.cc.civlit.domain.service.LevelOfCourtService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

import com.cc.civlit.domain.courtdivisions.FilingOffice;

/**
 * This is generated code - to edit code or override methods use - LevelOfCourt class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class LevelOfCourtBackingBeanBase extends BaseBackingBean<LevelOfCourt> {

	protected LevelOfCourt levelOfCourt = new LevelOfCourt();

	protected LevelOfCourtService levelOfCourtService;

	private List<FilingOffice> listFilingOffices = new ArrayList<FilingOffice>();

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public LevelOfCourt getLevelOfCourt() {
		return levelOfCourt;
	}

	public void setLevelOfCourt(LevelOfCourt levelOfCourt) {
		this.levelOfCourt = levelOfCourt;
	}

	public void setLevelOfCourtService(LevelOfCourtService levelOfCourtService) {
		this.levelOfCourtService = levelOfCourtService;
	}

	public LevelOfCourtService getLevelOfCourtService() {
		return this.levelOfCourtService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<LevelOfCourt> getBaseService() {
		return levelOfCourtService;
	}

	public LevelOfCourt getEntity() {
		return getLevelOfCourt();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		levelOfCourt = new LevelOfCourt();
		resetRanges();

		listFilingOffices.clear();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		levelOfCourt = levelOfCourtService.load(id);

	}

	@Override
	public String update() {

		addFilingOfficesToLevelOfCourt();

		return super.update();
	}

	public List<FilingOffice> getListFilingOffices() {
		if (listFilingOffices.isEmpty())
			loadFilingOffices();

		return listFilingOffices;
	}

	public void setListFilingOffices(List<FilingOffice> listFilingOffices) {
		this.listFilingOffices = listFilingOffices;
	}

	private void loadFilingOffices() {
		listFilingOffices.clear();
		if (levelOfCourt != null) {
			listFilingOffices.addAll(levelOfCourt.getFilingOffice());
		}
		int sizeOfExistingElements = listFilingOffices.size();
		// add a few spare rows - lets say parent has 3 children and we need to
		// show 5 rows - then add 2 rows with 2 new parents
		for (int i = 0; i < INITIAL_RECORDS - sizeOfExistingElements; i++) {
			listFilingOffices.add(new FilingOffice());
		}
	}

	private void addFilingOfficesToLevelOfCourt() {
		levelOfCourt.getFilingOffice().clear();
		List<FilingOffice> listValidFilingOffices = new ArrayList<FilingOffice>();

		for (FilingOffice filingOffice : listFilingOffices) {

			filingOffice.setLevelOfCourt(levelOfCourt);
			listValidFilingOffices.add(filingOffice);

		}

		levelOfCourt.getFilingOffice().addAll(listValidFilingOffices);
	}

	/**
	 * @param actionEvent
	 */
	public void addNewFilingOfficeRow(ActionEvent actionEvent) {
		listFilingOffices.add(new FilingOffice());
	}

	/**
	 * @param actionEvent
	 */
	public void deleteFilingOfficeRow(ActionEvent actionEvent) {
		String rowIndex = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get(
						"deleteRowIndex");

		int index = Integer.parseInt(rowIndex);
		FilingOffice filingOffice = listFilingOffices.get(index);
		listFilingOffices.remove(index);

		/*
			TaskService taskService = (TaskService) BeanHelper
					.getBean("taskService");

			if (task.getId() != null && task.getId() > 0) {
				taskService.delete(task);
			}*/
	}

}
