package com.oreon.kgauge.web.jsf;

import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.kgauge.domain.Exam;
import com.oreon.kgauge.service.ExamService;

import java.util.Date;
import java.util.List;
import org.witchcraft.model.support.Range;

/**
 * This is generated code - to edit code or override methods use - Exam class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

public class ExamBackingBeanBase extends BaseBackingBean<Exam> {

	private Exam exam = new Exam();

	private ExamService examService;

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

	@Override
	protected List<Range> getRangeList() {

		List<Range> listRanges = super.getRangeList();

		listRanges.add(rangeQuestions);

		listRanges.add(rangeDuration);

		listRanges.add(rangePrice);

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
		exam = examService.load(id);
		if (actionEvent.getComponent().getId() == "deleteId") {
			getBaseService().delete(exam);
		}
		/*
		UIParameter component = (UIParameter) actionEvent.getComponent().findComponent("editId");
		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());
		 */
	}

}
