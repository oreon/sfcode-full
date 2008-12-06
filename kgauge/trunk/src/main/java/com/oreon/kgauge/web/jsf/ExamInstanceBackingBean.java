package com.oreon.kgauge.web.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.witchcraft.model.jsf.JSFUtils;

import com.oreon.kgauge.domain.AnsweredQuestion;
import com.oreon.kgauge.domain.Question;

public class ExamInstanceBackingBean extends ExamInstanceBackingBeanBase {

	private List<AnsweredQuestion> questions;
	private int currentQuestionIndex = 0; 
	

	private static final Logger log = Logger
			.getLogger(ExamInstanceBackingBean.class);
		

	public List<AnsweredQuestion> getQuestions() {
		if (questions == null ) {
			// reloadFromId(examInstance.getId());
			 questions = new ArrayList<AnsweredQuestion>();
			 questions.addAll(examInstance.getAnsweredQuestion());
			 log.info("Loaded questions " + questions.size());
		}
		return questions;
	}

	public void setQuestions(List<AnsweredQuestion> questions) {
		this.questions = questions;
	}

	public AnsweredQuestion getCurrentQuestion() {
		
		if (!getQuestions().isEmpty()) {
			log.info("Returning question " + currentQuestionIndex + " "
					+ getQuestions().get(currentQuestionIndex).getQuestion().getQuestionText());
			
			return getQuestions().get(currentQuestionIndex);
		}else{
			log.info("No More questions returning null ");
		}

		return null;
	}
	
	public List<SelectItem> getChoicesAsSelectItems(){
		//need to reload question to avoid hibernate LIE
		List<AnsweredQuestion> ansQuestions = getQuestions();
		if(ansQuestions.isEmpty()){
			log.warn("There are no choices for this question");
			return new ArrayList<SelectItem>();
		}
		Question question = ansQuestions.get(currentQuestionIndex).getQuestion();
		QuestionBackingBean questionBackingBean = getBean("questionCrudBacking");
		questionBackingBean.reloadFromId(question.getId());
		question = questionBackingBean.getQuestion();
		return JSFUtils.getAsSelectItems(question.getAnswerChoice());
	}
	
	public String nextQuestion(){
		if(currentQuestionIndex < getTotalQuestions() - 1)
			currentQuestionIndex++;
		return "hasMoreQuestions";
	}
	
	public String previousQuestion(){
		if(currentQuestionIndex > 0)
			currentQuestionIndex--;
		return "hasMoreQuestions";
	}

	
	public int getTotalQuestions(){
		return getListAnsweredQuestions().size();
	}

	public int getCurrentQuestionIndex() {
		return currentQuestionIndex + 1;
	}

	public void setCurrentQuestionIndex(int currentQuestionIndex) {
		this.currentQuestionIndex = currentQuestionIndex;
	}
	
	
	/*
	public void setAnswerChoice(AnswerChoice answerChoice){
		Set<AnswerChoice> answerChoices = getCurrentQuestion().getAnswerChoice();
		if(answerChoices.isEmpty()){
			answerChoices.add(answerChoice);
		}
		else {
			AnswerChoice answerChoiceTemp = answerChoices.iterator().next(); 
			answerChoiceTemp= answerChoice;
		}
		AnsweredQuestionBackingBeanBase answeredQuestionBacking = getBean("answeredQuestionCrudBacking");
		answeredQuestionBacking.getAnsweredQuestionService().save(getCurrentQuestion());
	}*/
	
	public String showResult(){
		getExamInstanceService().save(examInstance);
		reloadFromId(examInstance.getId());
		return "endOfQuestions" ;
	}
	
	public String exitExam(){
		System.out.println("exitiExam called");
		log.debug("exitExam");
		reset();
		return "exitExam";
	}
	
	
	
	@Override
	public void reset() {
		super.reset();
		questions = null;
		currentQuestionIndex = 0;
	}

}
