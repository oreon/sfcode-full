package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.oreon.kgauge.domain.ExamInstance;
import com.oreon.kgauge.service.ExamInstanceService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

import com.oreon.kgauge.domain.AnsweredQuestion;

/**
 * This is generated code - to edit code or override methods use - ExamInstance class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class ExamInstanceBackingBeanBase extends BaseBackingBean<ExamInstance> {

	protected ExamInstance examInstance = new ExamInstance();

	protected ExamInstanceService examInstanceService;

	private List<AnsweredQuestion> listAnsweredQuestions = new ArrayList<AnsweredQuestion>();

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	private Range<Integer> rangeMaxScore = new Range<Integer>("maxScore");

	public Range<Integer> getRangeMaxScore() {
		return rangeMaxScore;
	}

	public void setRangeMaxScore(Range<Integer> rangeMaxScore) {
		this.rangeMaxScore = rangeMaxScore;
	}

	private Range<Integer> rangeCandidateScore = new Range<Integer>(
			"candidateScore");

	public Range<Integer> getRangeCandidateScore() {
		return rangeCandidateScore;
	}

	public void setRangeCandidateScore(Range<Integer> rangeCandidateScore) {
		this.rangeCandidateScore = rangeCandidateScore;
	}

	public ExamInstance getExamInstance() {
		return examInstance;
	}

	public void setExamInstance(ExamInstance examInstance) {
		this.examInstance = examInstance;
	}

	public void setExamInstanceService(ExamInstanceService examInstanceService) {
		this.examInstanceService = examInstanceService;
	}

	public ExamInstanceService getExamInstanceService() {
		return this.examInstanceService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<ExamInstance> getBaseService() {
		return examInstanceService;
	}

	public ExamInstance getEntity() {
		return getExamInstance();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

		examInstance.setCandidate(facades.ServiceFacade.getInstance()
				.getCandidateService().findByUsername(
						getLoggedInUser().getUsername()));

	}

	public void reset() {
		examInstance = new ExamInstance();
		resetRanges();

		listAnsweredQuestions.clear();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeMaxScore);

		listRanges.add(rangeCandidateScore);

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		examInstance = examInstanceService.load(id);

	}

	@Override
	public String update() {

		addAnsweredQuestionsToExamInstance();

		return super.update();
	}

	public List<AnsweredQuestion> getListAnsweredQuestions() {
		if (listAnsweredQuestions.isEmpty())
			loadAnsweredQuestions();

		return listAnsweredQuestions;
	}

	public void setListAnsweredQuestions(
			List<AnsweredQuestion> listAnsweredQuestions) {
		this.listAnsweredQuestions = listAnsweredQuestions;
	}

	private void loadAnsweredQuestions() {
		listAnsweredQuestions.clear();
		if (examInstance != null) {
			listAnsweredQuestions.addAll(examInstance.getAnsweredQuestion());
		}
		int sizeOfExistingElements = listAnsweredQuestions.size();
		// add a few spare rows - lets say parent has 3 children and we need to
		// show 5 rows - then add 2 rows with 2 new parents
		for (int i = 0; i < INITIAL_RECORDS - sizeOfExistingElements; i++) {
			listAnsweredQuestions.add(new AnsweredQuestion());
		}
	}

	private void addAnsweredQuestionsToExamInstance() {
		examInstance.getAnsweredQuestion().clear();
		List<AnsweredQuestion> listValidAnsweredQuestions = new ArrayList<AnsweredQuestion>();

		for (AnsweredQuestion answeredQuestion : listAnsweredQuestions) {

			answeredQuestion.setExamInstance(examInstance);
			listValidAnsweredQuestions.add(answeredQuestion);

		}

		examInstance.getAnsweredQuestion().addAll(listValidAnsweredQuestions);
	}

	/**
	 * @param actionEvent
	 */
	public void addNewAnsweredQuestionRow(ActionEvent actionEvent) {
		listAnsweredQuestions.add(new AnsweredQuestion());
	}

	/**
	 * @param actionEvent
	 */
	public void deleteAnsweredQuestionRow(ActionEvent actionEvent) {
		String rowIndex = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get(
						"deleteRowIndex");

		int index = Integer.parseInt(rowIndex);
		AnsweredQuestion answeredQuestion = listAnsweredQuestions.get(index);
		listAnsweredQuestions.remove(index);

		/*
			TaskService taskService = (TaskService) BeanHelper
					.getBean("taskService");

			if (task.getId() != null && task.getId() > 0) {
				taskService.delete(task);
			}*/
	}

}
