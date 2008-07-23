package com.cc.civlit.domain.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.cc.civlit.domain.courtdivisions.Jurisdiction;
import com.cc.civlit.domain.service.JurisdictionService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

import com.cc.civlit.domain.courtdivisions.LevelOfCourt;

/**
 * This is generated code - to edit code or override methods use - Jurisdiction class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class JurisdictionBackingBeanBase extends BaseBackingBean<Jurisdiction> {

	protected Jurisdiction jurisdiction = new Jurisdiction();

	protected JurisdictionService jurisdictionService;

	private List<LevelOfCourt> listLevelOfCourts = new ArrayList<LevelOfCourt>();

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public Jurisdiction getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(Jurisdiction jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public void setJurisdictionService(JurisdictionService jurisdictionService) {
		this.jurisdictionService = jurisdictionService;
	}

	public JurisdictionService getJurisdictionService() {
		return this.jurisdictionService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Jurisdiction> getBaseService() {
		return jurisdictionService;
	}

	public Jurisdiction getEntity() {
		return getJurisdiction();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		jurisdiction = new Jurisdiction();
		resetRanges();

		listLevelOfCourts.clear();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		jurisdiction = jurisdictionService.load(id);

	}

	@Override
	public String update() {

		addLevelOfCourtsToJurisdiction();

		return super.update();
	}

	public List<LevelOfCourt> getListLevelOfCourts() {
		if (listLevelOfCourts.isEmpty())
			loadLevelOfCourts();

		return listLevelOfCourts;
	}

	public void setListLevelOfCourts(List<LevelOfCourt> listLevelOfCourts) {
		this.listLevelOfCourts = listLevelOfCourts;
	}

	private void loadLevelOfCourts() {
		listLevelOfCourts.clear();
		if (jurisdiction != null) {
			listLevelOfCourts.addAll(jurisdiction.getLevelOfCourt());
		}
		int sizeOfExistingElements = listLevelOfCourts.size();
		// add a few spare rows - lets say parent has 3 children and we need to
		// show 5 rows - then add 2 rows with 2 new parents
		for (int i = 0; i < INITIAL_RECORDS - sizeOfExistingElements; i++) {
			listLevelOfCourts.add(new LevelOfCourt());
		}
	}

	private void addLevelOfCourtsToJurisdiction() {
		jurisdiction.getLevelOfCourt().clear();
		List<LevelOfCourt> listValidLevelOfCourts = new ArrayList<LevelOfCourt>();

		for (LevelOfCourt levelOfCourt : listLevelOfCourts) {

			levelOfCourt.setJurisdiction(jurisdiction);
			listValidLevelOfCourts.add(levelOfCourt);

		}

		jurisdiction.getLevelOfCourt().addAll(listValidLevelOfCourts);
	}

	/**
	 * @param actionEvent
	 */
	public void addNewLevelOfCourtRow(ActionEvent actionEvent) {
		listLevelOfCourts.add(new LevelOfCourt());
	}

	/**
	 * @param actionEvent
	 */
	public void deleteLevelOfCourtRow(ActionEvent actionEvent) {
		String rowIndex = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get(
						"deleteRowIndex");

		int index = Integer.parseInt(rowIndex);
		LevelOfCourt levelOfCourt = listLevelOfCourts.get(index);
		listLevelOfCourts.remove(index);

		/*
			TaskService taskService = (TaskService) BeanHelper
					.getBean("taskService");

			if (task.getId() != null && task.getId() > 0) {
				taskService.delete(task);
			}*/
	}

}
