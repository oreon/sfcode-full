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

			exam.setDuration(1932);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public org.wc.trackrite.onepack.Exam createExamTwo() {
		org.wc.trackrite.onepack.Exam exam = new org.wc.trackrite.onepack.Exam();

		try {

			exam.setName("Eric");

			exam.setDuration(7422);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public org.wc.trackrite.onepack.Exam createExamThree() {
		org.wc.trackrite.onepack.Exam exam = new org.wc.trackrite.onepack.Exam();

		try {

			exam.setName("beta");

			exam.setDuration(6766);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public org.wc.trackrite.onepack.Exam createExamFour() {
		org.wc.trackrite.onepack.Exam exam = new org.wc.trackrite.onepack.Exam();

		try {

			exam.setName("John");

			exam.setDuration(7902);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public org.wc.trackrite.onepack.Exam createExamFive() {
		org.wc.trackrite.onepack.Exam exam = new org.wc.trackrite.onepack.Exam();

		try {

			exam.setName("zeta");

			exam.setDuration(1247);

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public org.wc.trackrite.onepack.Exam getRandomRecord() {

		if (exams.isEmpty()) {
			createAll();
		}

		return exams.get(new Random().nextInt(exams.size()));
	}

	public List<org.wc.trackrite.onepack.Exam> createAll() {
		createExamOne();
		createExamTwo();
		createExamThree();
		createExamFour();
		createExamFive();

		return exams;
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
