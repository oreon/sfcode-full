package com.oreon.kgauge.web.jsf;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.oreon.kgauge.bizlogic.ExamFactory;
import com.oreon.kgauge.domain.Candidate;
import com.oreon.kgauge.domain.ExamInstance;

import facades.ServiceFacade;


public class ExamBackingBean extends ExamBackingBeanBase {

	private static final Logger log = Logger.getLogger(ExamBackingBean.class);
	
	private ExamFactory examFactory;
	
	public ExamFactory getExamFactory() {
		return examFactory;
	}

	public void setExamFactory(ExamFactory examFactory) {
		this.examFactory = examFactory;
	}

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
	
	public String launchExam(){
		CandidateBackingBean candidateBacking = getBean("candidateCrudBacking");
		Candidate candidate = candidateBacking.getLoggedinCandidate();
		if(candidate == null)
			return NavigationConstants.LOGIN_OR_REGISTER;

		exam = examService.load(exam.getId());
		examFactory = ServiceFacade.getInstance().getRandomQuestionsExamFactoryImpl();
		ExamInstance instance = examFactory.createExam(exam, null);
		ExamInstanceBackingBean examInstanceBackingBean = getBean("examInstanceCrudBacking");
		
					
		instance.setCandidate(candidate);
		examInstanceBackingBean.setExamInstance(instance);
		examInstanceBackingBean.getExamInstanceService().save(instance);
		
		log.info("Launching exam " + instance.getExam().getName() + " " + instance.getAnsweredQuestionCount() + "questions ");
		
		
		return NavigationConstants.BEGIN_EXAM;
	}
	
	public List getActiveExams(){
		return examService.findActiveExams();
	}
}
