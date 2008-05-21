package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;
import org.apache.commons.lang.StringUtils;

import com.oreon.kgauge.domain.Exam;
import com.oreon.kgauge.service.ExamService;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import org.witchcraft.model.support.Range;

import com.oreon.kgauge.domain.Section;

/**
 * This is generated code - to edit code or override methods use - Exam class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class ExamBackingBeanBase extends BaseBackingBean<Exam> {

	protected Exam exam = new Exam();

	protected ExamService examService;

	private List<Section> listSections = new ArrayList<Section>();

	private Range<Date> rangeCreationDate = new Range<Date>("dateCreated");

	public Range<Date> getRangeCreationDate() {
		return rangeCreationDate;
	}

	public void setRangeCreationDate(Range<Date> rangeCreationDate) {
		this.rangeCreationDate = rangeCreationDate;
	}

	private Range<Integer> rangeQuestions = new Range<Integer>("questions");

	public Range<Integer> getRangeQuestions() {
		return rangeQuestions;
	}

	public void setRangeQuestions(Range<Integer> rangeQuestions) {
		this.rangeQuestions = rangeQuestions;
	}

	private Range<Integer> rangeDuration = new Range<Integer>("duration");

	public Range<Integer> getRangeDuration() {
		return rangeDuration;
	}

	public void setRangeDuration(Range<Integer> rangeDuration) {
		this.rangeDuration = rangeDuration;
	}

	private Range<Double> rangePrice = new Range<Double>("price");

	public Range<Double> getRangePrice() {
		return rangePrice;
	}

	public void setRangePrice(Range<Double> rangePrice) {
		this.rangePrice = rangePrice;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public Exam getExam() {
		return exam;
	}

	public void set(Exam exam) {
		this.exam = exam;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Exam> getBaseService() {
		return examService;
	}

	public Exam getEntity() {
		return getExam();
	}

	/**
	 * Any initializations of the member entity should be done in this method - 
	 * It will be called before add new action
	 */
	public void initForAddNew() {

		exam.setExamCreator(facades.ServiceFacade.getInstance()
				.getExamCreatorService().findByUsername(
						getLoggedInUser().getUsername()));

	}

	public void reset() {
		exam = new Exam();
		resetRanges();

		listSections.clear();

	}

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeQuestions);

		listRanges.add(rangeDuration);

		listRanges.add(rangePrice);

		listRanges.add(rangeCreationDate);
		return listRanges;
	}

	protected void reloadFromId(long id) {
		exam = examService.load(id);

	}

	@Override
	public String update() {

		addSectionsToExam();

		return super.update();
	}

	public List<Section> getListSections() {
		if (listSections.isEmpty())
			loadSections();

		return listSections;
	}

	public void setListSections(List<Section> listSections) {
		this.listSections = listSections;
	}

	private void loadSections() {
		listSections.clear();
		if (exam != null) {
			listSections.addAll(exam.getSection());
		}
		int sizeOfExistingElements = listSections.size();
		// add a few spare rows - lets say parent has 3 children and we need to
		// show 5 rows - then add 2 rows with 2 new parents
		for (int i = 0; i < INITIAL_RECORDS - sizeOfExistingElements; i++) {
			listSections.add(new Section());
		}
	}

	private void addSectionsToExam() {
		exam.getSection().clear();
		List<Section> listValidSections = new ArrayList<Section>();

		for (Section section : listSections) {

			section.setExam(exam);
			listValidSections.add(section);

		}

		exam.getSection().addAll(listValidSections);
	}

	/**
	 * @param actionEvent
	 */
	public void addNewSectionRow(ActionEvent actionEvent) {
		listSections.add(new Section());
	}

	/**
	 * @param actionEvent
	 */
	public void deleteSectionRow(ActionEvent actionEvent) {
		String rowIndex = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap().get(
						"deleteRowIndex");

		int index = Integer.parseInt(rowIndex);
		Section section = listSections.get(index);
		listSections.remove(index);

		/*
			TaskService taskService = (TaskService) BeanHelper
					.getBean("taskService");

			if (task.getId() != null && task.getId() > 0) {
				taskService.delete(task);
			}*/
	}

}
