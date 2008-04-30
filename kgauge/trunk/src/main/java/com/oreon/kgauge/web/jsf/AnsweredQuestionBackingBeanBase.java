package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.oreon.kgauge.domain.AnsweredQuestion;
import com.oreon.kgauge.service.AnsweredQuestionService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - AnsweredQuestion class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class AnsweredQuestionBackingBeanBase
		extends
			BaseBackingBean<AnsweredQuestion> {

	protected AnsweredQuestion answeredQuestion = new AnsweredQuestion();

	protected AnsweredQuestionService answeredQuestionService;

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public void setAnsweredQuestionService(
			AnsweredQuestionService answeredQuestionService) {
		this.answeredQuestionService = answeredQuestionService;
	}

	public AnsweredQuestion getAnsweredQuestion() {
		return answeredQuestion;
	}

	public void set(AnsweredQuestion answeredQuestion) {
		this.answeredQuestion = answeredQuestion;
	}

	@SuppressWarnings("unchecked")
	public BaseService<AnsweredQuestion> getBaseService() {
		return answeredQuestionService;
	}

	public AnsweredQuestion getEntity() {
		return getAnsweredQuestion();
	}

	public void reset() {
		answeredQuestion = new AnsweredQuestion();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		answeredQuestion = answeredQuestionService.load(id);
	}

}
