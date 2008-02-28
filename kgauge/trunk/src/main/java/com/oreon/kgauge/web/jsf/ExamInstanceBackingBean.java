package com.oreon.kgauge.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;
import javax.faces.component.UIOutput;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.kgauge.domain.ExamInstance;
import com.oreon.kgauge.service.ExamInstanceService;

public class ExamInstanceBackingBean extends BaseBackingBean<ExamInstance> {

	private ExamInstance examInstance = new ExamInstance();

	private ExamInstanceService examInstanceService;

	public void setExamInstanceService(ExamInstanceService examInstanceService) {
		this.examInstanceService = examInstanceService;
	}

	public ExamInstance getExamInstance() {
		return examInstance;
	}

	public void set(ExamInstance examInstance) {
		this.examInstance = examInstance;
	}

	@SuppressWarnings("unchecked")
	public BaseService<ExamInstance> getBaseService() {
		return examInstanceService;
	}

	public ExamInstance getEntity() {
		return getExamInstance();
	}

	public String search() {
		action = SEARCH;
		return "search";
	}
	//functions for advanced search

	public String dateOp = "On";

	public String dateOp() {
		System.out.println("getting date op from method: " + dateOp);
		return dateOp;
	}

	public void doAdvancedSearch() {
		switch (dateOP) {
			case "On" :
				;
				break;
			case "Any" :
				;
				break;
			case "After" :
				;
				break;
			case "Before" :
				;
				break;
			case "Between" :
				;
				break;
			default :
				;
				break;
		}

	}
	public String getDateOp() {
		if (dateOp == null) {
			dateOp = "Any";
		}
		System.out.println("getting date op: " + dateOp);
		return dateOp;
	}

	public void setDateOp(UIParameter uip) {
		//dateOp=(value)uip.getId();
		System.out.println("setting date op from param: " + dateOp);
	}

	public void setDateOp(ActionEvent actionEvent) {
		dateOp = actionEvent.getComponent().getId();
		System.out.println("setting date op from event: " + dateOp);
	}

	public void setDateOp(String s) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		this.dateOp = (String) ctx.getExternalContext()
				.getRequestParameterMap().get("id");
		System.out.println("setting date op from string: " + dateOp);
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
		examInstance = examInstanceService.load(id);
		if (actionEvent.getComponent().getId() == "deleteId") {
			getBaseService().delete(examInstance);
		}
		/*
		UIParameter component = (UIParameter) actionEvent.getComponent().findComponent("editId");
		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());
		 */
	}

}
