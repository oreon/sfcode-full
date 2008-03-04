package com.oreon.kgauge.web.jsf;

import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.context.FacesContext;
import javax.faces.component.UIOutput;

import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.service.BaseService;

import com.oreon.kgauge.domain.Question;
import com.oreon.kgauge.service.QuestionService;

public class QuestionBackingBean extends BaseBackingBean<Question> {

	private Question question = new Question();

	private QuestionService questionService;

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public Question getQuestion() {
		return question;
	}

	public void set(Question question) {
		this.question = question;
	}

	@SuppressWarnings("unchecked")
	public BaseService<Question> getBaseService() {
		return questionService;
	}

	public Question getEntity() {
		return getQuestion();
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

	/*
	public void doAdvancedSearch(){
		switch(dateOp){
		case "On":;break;
		case "Any":;break;
		case "After":;break;
		case "Before":;break;
		case "Between":;break;
		default:;break;
		}
	}*/

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
		question = questionService.load(id);
		if (actionEvent.getComponent().getId() == "deleteId") {
			getBaseService().delete(question);
		}
		/*
		UIParameter component = (UIParameter) actionEvent.getComponent().findComponent("editId");
		// parse the value of the UIParameter component    	 
		long id = Long.parseLong(component.getValue().toString());
		 */
	}

}
