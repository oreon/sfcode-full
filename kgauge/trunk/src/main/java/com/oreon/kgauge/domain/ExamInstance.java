package com.oreon.kgauge.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

@Entity
public class ExamInstance extends ExamInstanceBase implements
		java.io.Serializable {

	private static final Logger log = Logger.getLogger(ExamInstance.class);
	private static final long serialVersionUID = 1L;

	/* Default Constructor */
	public ExamInstance() {
	}

	public ExamInstance examInstanceInstance() {
		return this;
	}

	@Transient
	public Integer getMaxScore() {
		int score = 0;
		Set<AnsweredQuestion> answeredQuestions = getAnsweredQuestion();
		for (AnsweredQuestion answeredQuestion : answeredQuestions) {
			Set<AnswerChoice> answerChoices = answeredQuestion.getQuestion()
					.getAnswerChoice();
			int maxScore = 0;

			for (AnswerChoice answerChoice : answerChoices) {
				int answerChoiceScore = answerChoice.getScore() == null ? 0
						: answerChoice.getScore();
				if (answerChoiceScore > maxScore) {
					maxScore = answerChoice.getScore();
				}
			}

			if (maxScore == 0)
				maxScore = getExam().getDefaultMarksForCorrect();

			score += maxScore;
		}
		return score;
	}

	public void setMaxScore(Integer score) {

	}

	@Transient
	@Override
	public Integer getCandidateScore() {
		int score = 0;
		Set<AnsweredQuestion> answeredQuestions = getAnsweredQuestion();
		for (AnsweredQuestion answeredQuestion : answeredQuestions) {
			 AnswerChoice answerChoice = answeredQuestion.getAnswerChoice();
			 
			 if(answerChoice == null) continue;
			 
			int answerChoiceScore = answerChoice.getScore() == null ? 0
					: answerChoice.getScore();

			if (answerChoiceScore == 0 && answerChoice.isCorrectChoice())
				answerChoiceScore = getExam().getDefaultMarksForCorrect();

			score += answerChoiceScore;

		}
		return score;
	}

}
