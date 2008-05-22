package com.oreon.kgauge.web.jsf;

import java.util.List;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;

import com.oreon.kgauge.domain.Exam;

import javax.jws.WebService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.*;


public class ExamBackingBean extends ExamBackingBeanBase {

	private static final Logger log = Logger.getLogger(ExamBackingBean.class);
	
	
	public void selectExam(){		
		FacesContext context = FacesContext.getCurrentInstance();
		String idStr = (String) context.getExternalContext().getRequestParameterMap().get("id");
		long id = Long.parseLong(idStr);
		exam = examService.load(id);
				
	}
	
	public void selectExam(ActionEvent e){	 
		FacesContext context = FacesContext.getCurrentInstance();
		String idStr = (String) context.getExternalContext().getRequestParameterMap().get("id");
		long id = Long.parseLong(idStr);
		exam = examService.load(id);					
	}
	
	/**
	 * Get a list of Records - if action is search, get a subset otherwise get
	 * all
	 * 
	 * @return - a list of records of type T
	 */
	public List<Exam> getRecords() {
		List<Exam> entities = null;
		if (action == null)
			entities = getBaseService().loadAll();

		if (action.equals(SEARCH))
			entities = getBaseService().searchByExample(getEntity(),
					getRangeList());
		else if(action.equals("textSearch"))
			entities = getBaseService().performTextSearch(getSearchText());
		
		createSuccessMessage("Found " + entities.size() + " result(s).");

		return entities;
	}

	public String textSearch() {
		action = "textSearch";
		log.debug("setting action to textsearch");
		return "successTextSearchExams";
	}

	
	
	public String getName(){
		return this.exam.getName();
	}
}
