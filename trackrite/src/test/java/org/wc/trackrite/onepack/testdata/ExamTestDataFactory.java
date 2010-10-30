package org.wc.trackrite.onepack.testdata;

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
			AbstractTestDataFactory<org.wc.trackrite.onepack.Exam> {

	private List<org.wc.trackrite.onepack.Exam> exams = new ArrayList<org.wc.trackrite.onepack.Exam>();

	private static final Logger logger = Logger
			.getLogger(ExamTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.onepack.ExamAction examAction;

	public void register(org.wc.trackrite.onepack.Exam exam) {
		exams.add(exam);
	}

	public org.wc.trackrite.onepack.Exam createExamOne() {
		org.wc.trackrite.onepack.Exam exam = new org.wc.trackrite.onepack.Exam();

		try {

			exam.setName("Mark");

			exam.setDuration(8230);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public org.wc.trackrite.onepack.Exam createExamTwo() {
		org.wc.trackrite.onepack.Exam exam = new org.wc.trackrite.onepack.Exam();

		try {

			exam.setName("pi");

			exam.setDuration(1302);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public org.wc.trackrite.onepack.Exam createExamThree() {
		org.wc.trackrite.onepack.Exam exam = new org.wc.trackrite.onepack.Exam();

		try {

			exam.setName("Wilson");

			exam.setDuration(5132);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public org.wc.trackrite.onepack.Exam createExamFour() {
		org.wc.trackrite.onepack.Exam exam = new org.wc.trackrite.onepack.Exam();

		try {

			exam.setName("gamma");

			exam.setDuration(8194);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public org.wc.trackrite.onepack.Exam createExamFive() {
		org.wc.trackrite.onepack.Exam exam = new org.wc.trackrite.onepack.Exam();

		try {

			exam.setName("Eric");

			exam.setDuration(2698);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public List<org.wc.trackrite.onepack.Exam> createAll() {
		createExamOne();
		createExamTwo();
		createExamThree();
		createExamFour();
		createExamFive();

		return exams;
	}

	@Override
	public List<org.wc.trackrite.onepack.Exam> getListOfRecords() {
		return exams;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.onepack.Exam e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.onepack.Exam exam : exams) {
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
