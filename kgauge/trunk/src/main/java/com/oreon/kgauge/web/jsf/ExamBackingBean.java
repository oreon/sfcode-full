package com.oreon.kgauge.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.kgauge.domain.Exam;
import com.oreon.kgauge.service.ExamService;

public class ExamBackingBean extends BaseBackingBean<Exam> {

	private Exam exam = new Exam();

	private ExamService examService;

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

	public String search() {
		action = SEARCH;
		return "search";
	}

	/** Returns a success string upon selection of an entity in model - majority of work is done
	 * in the actionListener selectEntity
	 * @return - "success" if everthing goes fine
	 * @see - 
	 */
	public String select() {
		return "edit";
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
