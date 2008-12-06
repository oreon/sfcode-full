package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import java.util.Set;
import org.apache.commons.collections.ListUtils;

import com.oreon.kgauge.domain.Question;
import com.oreon.kgauge.service.QuestionService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import org.witchcraft.model.support.Range;

import com.oreon.kgauge.domain.AnswerChoice;

/**
 * This is generated code - to edit code or override methods use - Question class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class QuestionBackingBeanBase extends BaseBackingBean<Question> {

	protected Question question = new Question();

	protected QuestionService questionService;

	private List<AnswerChoice> listAnswerChoices = new ArrayList<AnswerChoice>();

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	private Range<AnswerChoice> rangeCorrectChoice = new Range<AnswerChoice>(
			"correctChoice");

	public Range<AnswerChoice> getRangeCorrectChoice() {
		return rangeCorrectChoice;
	}

	public void setRangeCorrectChoice(Range<AnswerChoice> rangeCorrectChoice) {
		this.rangeCorrectChoice = rangeCorrectChoice;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public QuestionService getQuestionService() {
		return this.questionService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Question> getBaseService() {
		return questionService;
	}

	public Question getEntity() {
		return getQuestion();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		question = new Question();
		resetRanges();

		listAnswerChoices.clear();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCorrectChoice);

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		if (id != 0)
			question = questionService.load(id);

	}

	@Override
	public String update() {

		addAnswerChoicesToQuestion();

		return super.update();
	}

	public List<AnswerChoice> getListAnswerChoices() {
		if (listAnswerChoices.isEmpty())
			loadAnswerChoices();

		return listAnswerChoices;
	}

	public void setListAnswerChoices(List<AnswerChoice> listAnswerChoices) {
		this.listAnswerChoices = listAnswerChoices;
	}

	private void loadAnswerChoices() {
		listAnswerChoices.clear();
		if (question != null) {
			listAnswerChoices.addAll(question.getAnswerChoice());
		}
		int sizeOfExistingElements = listAnswerChoices.size();
		// add a few spare rows - lets say parent has 3 children and we need to
		// show 5 rows - then add 2 rows with 2 new parents
		for (int i = 0; i < INITIAL_RECORDS - sizeOfExistingElements; i++) {
			listAnswerChoices.add(new AnswerChoice());
		}
	}

	private void addAnswerChoicesToQuestion() {
		question = questionService.save(question);//To prevent lazy initialization exception
		question.getAnswerChoice().clear();
		List<AnswerChoice> listValidAnswerChoices = new ArrayList<AnswerChoice>();

		for (AnswerChoice answerChoice : listAnswerChoices) {

			answerChoice.setQuestion(question);
			listValidAnswerChoices.add(answerChoice);

		}

		question.getAnswerChoice().addAll(listValidAnswerChoices);
	}

	/**
	 * @param actionEvent
	 */
	public void addNewAnswerChoiceRow(ActionEvent actionEvent) {
		listAnswerChoices.add(new AnswerChoice());
	}

	/**
	 * @param actionEvent
	 */
	public void deleteAnswerChoiceRow(ActionEvent actionEvent) {
		String rowIndex = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get(
						"deleteRowIndex");

		int index = Integer.parseInt(rowIndex);
		AnswerChoice answerChoice = listAnswerChoices.get(index);
		listAnswerChoices.remove(index);

		/*
			TaskService taskService = (TaskService) BeanHelper
					.getBean("taskService");

			if (task.getId() != null && task.getId() > 0) {
				taskService.delete(task);
			}*/
	}

}
