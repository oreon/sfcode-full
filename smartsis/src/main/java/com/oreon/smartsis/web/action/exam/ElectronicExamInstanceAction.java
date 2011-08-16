package com.oreon.smartsis.web.action.exam;

import java.util.Set;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.exam.ElectronicExam;
import com.oreon.smartsis.exam.ElectronicExamEvent;
import com.oreon.smartsis.exam.ElectronicExamInstance;
import com.oreon.smartsis.exam.Question;
import com.oreon.smartsis.exam.QuestionInstance;

//@Scope(ScopeType.CONVERSATION)
@Name("electronicExamInstanceAction")
public class ElectronicExamInstanceAction extends
		ElectronicExamInstanceActionBase implements java.io.Serializable {
	
	String ss = 
		"SELECT q.text, IF( qi.selectedChoice = q.correctChoice, COUNT(*), 0 ) FROM question q  LEFT JOIN ( questionInstance qi  )" +
		" ON ( qi.selectedChoice = q.correctChoice AND qi.question_id = q.id  ) GROUP BY q.id  ";
	@Override
	public void prePopulateListQuestionInstances() {
		if (getInstance().getId() != null || !listQuestionInstances.isEmpty())
			return;

		ElectronicExam exam = getEntityManager().find(ElectronicExam.class, 1L);
		ElectronicExamEvent event = getEntityManager().find(ElectronicExamEvent.class, 1L);
		if(event == null)
			return;
		event.setElectronicExam(exam);

		getInstance().setElectronicExamEvent(event);

		Set<Question> quesitons =  exam//getInstance().getElectronicExamEvent().getElectronicExam()
				.getQuestions();

		for (Question question : quesitons) {
			QuestionInstance questionInstance = new QuestionInstance();

			questionInstance.setQuestion(question);

			questionInstance.setElectronicExamInstance(getInstance());

			listQuestionInstances.add(questionInstance);
		}
	}

	public Integer calculateScore() {
		return calculateScore(getInstance());
	}
	
	public Integer getCalculatedScore(){
		return calculateScore(getInstance());
	}

	public Integer calculateScore(ElectronicExamInstance electronicExamInstance) {
		Set<QuestionInstance> quesitons = electronicExamInstance
				.getQuestionInstances();
		int score = 0;

		for (QuestionInstance questionInstance : listQuestionInstances) {

			if (questionInstance.getSelectedChoice() != null) {
				if (questionInstance.getQuestion().getCorrectChoice() == questionInstance.getSelectedChoice() )
					score++;
			}
		}

		return score;
	}

	public static void main(String[] args) {
	
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for(int i = 0; i < 10 ; i ++)
		stats.addValue(i);
		
		System.out.println(stats.getMax());
		System.out.println(stats.getMin());
		System.out.println(stats.getKurtosis());
		System.out.println(stats.getGeometricMean());
		System.out.println(stats.getMean());
		System.out.println(stats.getVariance());
		System.out.println(stats.getStandardDeviation());
	}

	@Override
	public String save() {
		getInstance().setScore(calculateScore());
		return super.save();
	}

	public void getStats() {

	}
}
