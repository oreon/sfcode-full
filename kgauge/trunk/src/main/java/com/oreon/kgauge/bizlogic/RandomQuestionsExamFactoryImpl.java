package com.oreon.kgauge.bizlogic;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oreon.kgauge.domain.AnsweredQuestion;
import com.oreon.kgauge.domain.Exam;
import com.oreon.kgauge.domain.ExamInstance;
import com.oreon.kgauge.domain.Question;
import com.oreon.kgauge.domain.Section;

@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RandomQuestionsExamFactoryImpl extends
		RandomQuestionsExamFactoryImplBase {

	private static final Logger log = Logger
			.getLogger(RandomQuestionsExamFactoryImpl.class);

	@Override
	public ExamInstance createExam(Exam exam, java.util.Map additionalParameters) {
		
		log.info("Creating an exam instance for " + exam.getExamNumber() );
		
		super.createExam(exam, additionalParameters);

		ExamInstance instance = new ExamInstance();
		Set<Section> sections = exam.getSection();
		instance.setExam(exam);

		for (Section section : sections) {
			Set<Question> questions = section.getQuestion();

			for (Question question : questions) {
				AnsweredQuestion answeredQuestion = new AnsweredQuestion();
				answeredQuestion.setQuestion(question);
				instance.addAnsweredQuestion(answeredQuestion);
			}
		}

		return instance;
	}


}
