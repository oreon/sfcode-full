package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.oreon.kgauge.domain.AnswerChoice;
import com.oreon.kgauge.service.AnswerChoiceService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - AnswerChoice class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class AnswerChoiceBackingBeanBase extends BaseBackingBean<AnswerChoice> {

	protected AnswerChoice answerChoice = new AnswerChoice();

	protected AnswerChoiceService answerChoiceService;

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

	public AnswerChoice getAnswerChoice() {
		return answerChoice;
	}

	public void setAnswerChoice(AnswerChoice answerChoice) {
		this.answerChoice = answerChoice;
	}

	public void setAnswerChoiceService(AnswerChoiceService answerChoiceService) {
		this.answerChoiceService = answerChoiceService;
	}

	public AnswerChoiceService getAnswerChoiceService() {
		return this.answerChoiceService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<AnswerChoice> getBaseService() {
		return answerChoiceService;
	}

	public AnswerChoice getEntity() {
		return getAnswerChoice();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		answerChoice = new AnswerChoice();
		resetRanges();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeScore);

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		answerChoice = answerChoiceService.load(id);

	}

}
