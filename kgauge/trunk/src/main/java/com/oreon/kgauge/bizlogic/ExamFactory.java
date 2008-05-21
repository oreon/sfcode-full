package com.oreon.kgauge.bizlogic;

/**
 * This interface defines a way to create ExamInstances from Exams. The implementing classes can use different strategies e.g random questions
 */
public interface ExamFactory {

	public com.oreon.kgauge.domain.ExamInstance createExam(
			com.oreon.kgauge.domain.Exam exam, Map additionalParameters);

}
