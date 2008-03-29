package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.kgauge.domain.AnswerChoice;
import com.oreon.kgauge.service.AnswerChoiceService;

import java.util.Date;
import java.util.List;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - AnswerChoice class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class AnswerChoiceBackingBeanBase extends BaseBackingBean<AnswerChoice> {

	private AnswerChoice answerChoice = new AnswerChoice();

	private AnswerChoiceService answerChoiceService;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	private Range<Integer> rangeScore = new Range<Integer>("score");

	public Range<Integer> getRangeScore() {
		return rangeScore;
	}

	public void setRangeScore(Range<Integer> rangeScore) {
		this.rangeScore = rangeScore;
	}

	public void setAnswerChoiceService(AnswerChoiceService answerChoiceService) {
		this.answerChoiceService = answerChoiceService;
	}

	public AnswerChoice getAnswerChoice() {
		return answerChoice;
	}

	public void set(AnswerChoice answerChoice) {
		this.answerChoice = answerChoice;
	}

	@SuppressWarnings("unchecked")
	public BaseService<AnswerChoice> getBaseService() {
		return answerChoiceService;
	}

	public AnswerChoice getEntity() {
		return getAnswerChoice();
	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeScore);

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
		answerChoice = answerChoiceService.load(id);
		if (actionEvent.getComponent().getId() == "deleteId") {
			getBaseService().delete(answerChoice);
		}
		/*
		UIParameter component = (UIParameter) actionEvent.getComponent().findComponent("editId");
		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());
		 */
	}

}
