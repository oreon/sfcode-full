package org.wc.trackrite.exams.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.action.test.AbstractTestDataFactory;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class ExamTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.exams.Exam> {

	private List<org.wc.trackrite.exams.Exam> exams = new ArrayList<org.wc.trackrite.exams.Exam>();

	private static final Logger logger = Logger
			.getLogger(ExamTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.exams.ExamAction examAction;

	public void register(org.wc.trackrite.exams.Exam exam) {
		exams.add(exam);
	}

	public org.wc.trackrite.exams.Exam createExamOne() {
		org.wc.trackrite.exams.Exam exam = new org.wc.trackrite.exams.Exam();

		try {

			exam.setName("delta");

			exam.setDuration(5969);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public org.wc.trackrite.exams.Exam createExamTwo() {
		org.wc.trackrite.exams.Exam exam = new org.wc.trackrite.exams.Exam();

		try {

			exam.setName("alpha");

			exam.setDuration(6430);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public org.wc.trackrite.exams.Exam createExamThree() {
		org.wc.trackrite.exams.Exam exam = new org.wc.trackrite.exams.Exam();

		try {

			exam.setName("Wilson");

			exam.setDuration(8882);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public org.wc.trackrite.exams.Exam createExamFour() {
		org.wc.trackrite.exams.Exam exam = new org.wc.trackrite.exams.Exam();

		try {

			exam.setName("alpha");

			exam.setDuration(3992);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public org.wc.trackrite.exams.Exam createExamFive() {
		org.wc.trackrite.exams.Exam exam = new org.wc.trackrite.exams.Exam();

		try {

			exam.setName("pi");

			exam.setDuration(8984);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public List<org.wc.trackrite.exams.Exam> createAll() {
		createExamOne();
		createExamTwo();
		createExamThree();
		createExamFour();
		createExamFive();

		return exams;
	}

	@Override
	public List<org.wc.trackrite.exams.Exam> getListOfRecords() {
		return exams;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.exams.Exam e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.exams.Exam exam : exams) {
			persist(exam);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new ExamTestDataFactory().persistAll();
	}

}
