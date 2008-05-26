package com.oreon.kgauge.web.jsf;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.oreon.kgauge.bizlogic.ExamFactory;
import com.oreon.kgauge.bizlogic.RandomQuestionsExamFactoryImpl;
import com.oreon.kgauge.domain.Candidate;
import com.oreon.kgauge.domain.Exam;
import com.oreon.kgauge.domain.ExamInstance;


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
	
	public String launchExam(){
		CandidateBackingBean candidateBacking = getBean("candidateCrudBacking");
		Candidate candidate = candidateBacking.getLoggedinCandidate();
		if(candidate == null)
			return "loginOrRegister";

		exam = examService.load(exam.getId());
		ExamInstance instance = examFactory.createExam(exam, null);
		ExamInstanceBackingBean examInstanceBackingBean = getBean("examInstanceCrudBacking");
		
					
		instance.setCandidate(candidate);
		examInstanceBackingBean.setExamInstance(instance);
		examInstanceBackingBean.getExamInstanceService().save(instance);
		
		log.info("Launching exam " + instance.getExam().getName() + " " + instance.getAnsweredQuestionCount() + "questions ");
		
		
		return "beginExam";
	}
}
