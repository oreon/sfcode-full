package com.oreon.kgauge.web.jsf;

import javax.persistence.*;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.log4j.Logger;
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
	
	
	public String getName(){
		return this.exam.getName();
		
	}
}
