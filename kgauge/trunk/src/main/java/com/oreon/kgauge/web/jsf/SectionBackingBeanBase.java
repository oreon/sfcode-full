package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import java.util.Set;
import org.apache.commons.collections.ListUtils;

import com.oreon.kgauge.domain.Section;
import com.oreon.kgauge.service.SectionService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import org.witchcraft.model.support.Range;

import com.oreon.kgauge.domain.Question;

/**
 * This is generated code - to edit code or override methods use - Section class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class SectionBackingBeanBase extends BaseBackingBean<Section> {

	protected Section section = new Section();

	protected SectionService sectionService;

	private List<Question> listQuestions = new ArrayList<Question>();

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public SectionService getSectionService() {
		return this.sectionService;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Section> getBaseService() {
		return sectionService;
	}

	public Section getEntity() {
		return getSection();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

	}

	public void reset() {
		section = new Section();
		resetRanges();

		listQuestions.clear();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		if (id != 0)
			section = sectionService.load(id);

	}

	@Override
	public String update() {

		addQuestionsToSection();

		return super.update();
	}

	public List<Question> getListQuestions() {
		if (listQuestions.isEmpty())
			loadQuestions();

		return listQuestions;
	}

	public void setListQuestions(List<Question> listQuestions) {
		this.listQuestions = listQuestions;
	}

	private void loadQuestions() {
		listQuestions.clear();
		if (section != null) {
			listQuestions.addAll(section.getQuestion());
		}
		int sizeOfExistingElements = listQuestions.size();
		// add a few spare rows - lets say parent has 3 children and we need to
		// show 5 rows - then add 2 rows with 2 new parents
		for (int i = 0; i < INITIAL_RECORDS - sizeOfExistingElements; i++) {
			listQuestions.add(new Question());
		}
	}

	private void addQuestionsToSection() {
		section.getQuestion().clear();
		List<Question> listValidQuestions = new ArrayList<Question>();

		for (Question question : listQuestions) {

			question.setSection(section);
			listValidQuestions.add(question);

		}

		section.getQuestion().addAll(listValidQuestions);
	}

	/**
	 * @param actionEvent
	 */
	public void addNewQuestionRow(ActionEvent actionEvent) {
		listQuestions.add(new Question());
	}

	/**
	 * @param actionEvent
	 */
	public void deleteQuestionRow(ActionEvent actionEvent) {
		String rowIndex = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get(
						"deleteRowIndex");

		int index = Integer.parseInt(rowIndex);
		Question question = listQuestions.get(index);
		listQuestions.remove(index);

		/*
			TaskService taskService = (TaskService) BeanHelper
					.getBean("taskService");

			if (task.getId() != null && task.getId() > 0) {
				taskService.delete(task);
			}*/
	}

}
