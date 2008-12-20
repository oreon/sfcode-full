package com.oreon.kgauge.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;

import com.oreon.kgauge.service.ExamService;

@Transactional
public class ExamTestDataFactory extends AbstractTestDataFactory<Exam> {

	private List<Exam> exams = new ArrayList<Exam>();

	private static final Logger logger = Logger
			.getLogger(ExamTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	ExamService examService;

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	public void register(Exam exam) {
		exams.add(exam);
	}

	public Exam createExamOne() {
		Exam exam = new Exam();

		try {

			exam.setExamNumber("alpha");
			exam.setName("Mark");
			exam.setQuestions(3674);
			exam.setDuration(2978);
			exam.setPrice(81.87);
			exam
					.setScoringStrategy(com.oreon.kgauge.domain.ScoringType.ScoreOnlyForCorrectAnswers);
			exam.setExamStatus(com.oreon.kgauge.domain.ExamStatus.INACTIVE);
			exam.setPassMarks(7250);
			exam.setDefaultMarksForCorrect(606);
			exam.setDescription("John");

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			exam
					.setCategory((com.oreon.kgauge.domain.Category) categoryTestDataFactory
							.loadOneRecord());

			TestDataFactory examCreatorTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examCreatorTestDataFactory");

			exam
					.setExamCreator((com.oreon.kgauge.domain.ExamCreator) examCreatorTestDataFactory
							.loadOneRecord());

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public Exam createExamTwo() {
		Exam exam = new Exam();

		try {

			exam.setExamNumber("gamma");
			exam.setName("Eric");
			exam.setQuestions(9941);
			exam.setDuration(3082);
			exam.setPrice(9.68);
			exam
					.setScoringStrategy(com.oreon.kgauge.domain.ScoringType.ScoreForAllAnswers);
			exam.setExamStatus(com.oreon.kgauge.domain.ExamStatus.INCOMPLETE);
			exam.setPassMarks(3594);
			exam.setDefaultMarksForCorrect(2856);
			exam.setDescription("gamma");

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			exam
					.setCategory((com.oreon.kgauge.domain.Category) categoryTestDataFactory
							.loadOneRecord());

			TestDataFactory examCreatorTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examCreatorTestDataFactory");

			exam
					.setExamCreator((com.oreon.kgauge.domain.ExamCreator) examCreatorTestDataFactory
							.loadOneRecord());

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public Exam createExamThree() {
		Exam exam = new Exam();

		try {

			exam.setExamNumber("Wilson");
			exam.setName("alpha");
			exam.setQuestions(6799);
			exam.setDuration(3737);
			exam.setPrice(97.3);
			exam
					.setScoringStrategy(com.oreon.kgauge.domain.ScoringType.ScoreOnlyForCorrectAnswers);
			exam.setExamStatus(com.oreon.kgauge.domain.ExamStatus.INACTIVE);
			exam.setPassMarks(2160);
			exam.setDefaultMarksForCorrect(806);
			exam.setDescription("Eric");

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			exam
					.setCategory((com.oreon.kgauge.domain.Category) categoryTestDataFactory
							.loadOneRecord());

			TestDataFactory examCreatorTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examCreatorTestDataFactory");

			exam
					.setExamCreator((com.oreon.kgauge.domain.ExamCreator) examCreatorTestDataFactory
							.loadOneRecord());

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public Exam createExamFour() {
		Exam exam = new Exam();

		try {

			exam.setExamNumber("zeta");
			exam.setName("Mark");
			exam.setQuestions(4519);
			exam.setDuration(1226);
			exam.setPrice(99.52);
			exam
					.setScoringStrategy(com.oreon.kgauge.domain.ScoringType.ScoreForAllAnswers);
			exam.setExamStatus(com.oreon.kgauge.domain.ExamStatus.ACTIVE);
			exam.setPassMarks(7905);
			exam.setDefaultMarksForCorrect(8910);
			exam.setDescription("theta");

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			exam
					.setCategory((com.oreon.kgauge.domain.Category) categoryTestDataFactory
							.loadOneRecord());

			TestDataFactory examCreatorTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examCreatorTestDataFactory");

			exam
					.setExamCreator((com.oreon.kgauge.domain.ExamCreator) examCreatorTestDataFactory
							.loadOneRecord());

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public Exam createExamFive() {
		Exam exam = new Exam();

		try {

			exam.setExamNumber("Mark");
			exam.setName("epsilon");
			exam.setQuestions(683);
			exam.setDuration(502);
			exam.setPrice(32.63);
			exam
					.setScoringStrategy(com.oreon.kgauge.domain.ScoringType.ScoreOnlyForCorrectAnswers);
			exam.setExamStatus(com.oreon.kgauge.domain.ExamStatus.INACTIVE);
			exam.setPassMarks(6602);
			exam.setDefaultMarksForCorrect(8034);
			exam.setDescription("Wilson");

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			exam
					.setCategory((com.oreon.kgauge.domain.Category) categoryTestDataFactory
							.loadOneRecord());

			TestDataFactory examCreatorTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examCreatorTestDataFactory");

			exam
					.setExamCreator((com.oreon.kgauge.domain.ExamCreator) examCreatorTestDataFactory
							.loadOneRecord());

			register(exam);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return exam;
	}

	public Exam loadOneRecord() {
		List<Exam> exams = examService.loadAll();

		if (exams.isEmpty()) {
			persistAll();
			exams = examService.loadAll();
		}

		return exams.get(new Random().nextInt(exams.size()));
	}

	public List<Exam> getAllAsList() {

		if (exams.isEmpty()) {

			createExamOne();
			createExamTwo();
			createExamThree();
			createExamFour();
			createExamFive();

		}

		return exams;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Exam exam : exams) {
			try {
				examService.save(exam);
			} catch (BusinessException be) {
				logger.warn(" Exam " + exam.getDisplayName()
						+ "couldn't be saved " + be.getMessage());
			}
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(RECORDS_TO_CREATE);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			Exam exam = createRandomExam();
			examService.save(exam);
		}
	}

	public Exam createRandomExam() {
		Exam exam = new Exam();

		exam.setExamNumber((String) RandomValueGeneratorFactory
				.createInstance("String"));
		exam.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		exam.setQuestions((Integer) RandomValueGeneratorFactory
				.createInstance("Integer"));
		exam.setDuration((Integer) RandomValueGeneratorFactory
				.createInstance("Integer"));
		exam.setPrice((Double) RandomValueGeneratorFactory
				.createInstance("Double"));
		exam.setScoringStrategy((ScoringType) RandomValueGeneratorFactory
				.createInstance("ScoringType"));
		exam.setExamStatus((ExamStatus) RandomValueGeneratorFactory
				.createInstance("ExamStatus"));
		exam.setPassMarks((Integer) RandomValueGeneratorFactory
				.createInstance("Integer"));
		exam.setDefaultMarksForCorrect((Integer) RandomValueGeneratorFactory
				.createInstance("Integer"));
		exam.setDescription((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		exam
				.setCategory((com.oreon.kgauge.domain.Category) categoryTestDataFactory
						.loadOneRecord());

		TestDataFactory examCreatorTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("examCreatorTestDataFactory");

		exam
				.setExamCreator((com.oreon.kgauge.domain.ExamCreator) examCreatorTestDataFactory
						.loadOneRecord());

		return exam;
	}

}
