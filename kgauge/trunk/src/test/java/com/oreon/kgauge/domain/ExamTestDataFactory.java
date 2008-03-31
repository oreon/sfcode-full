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

			exam.setDescription("epsilon");
			exam.setName("zeta");
			exam.setQuestions(5640);
			exam.setDuration(3918);
			exam.setPrice(47.64);
			exam
					.setScoringStrategy(com.oreon.kgauge.domain.ScoringType.ScoreOnlyForCorrectAnswers);
			exam.setExamStatus(com.oreon.kgauge.domain.ExamStatus.INCOMPLETE);

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

			exam.setDescription("Wilson");
			exam.setName("Eric");
			exam.setQuestions(4187);
			exam.setDuration(8858);
			exam.setPrice(97.53);
			exam
					.setScoringStrategy(com.oreon.kgauge.domain.ScoringType.ScoreForAllAnswers);
			exam.setExamStatus(com.oreon.kgauge.domain.ExamStatus.ACTIVE);

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

			exam.setDescription("epsilon");
			exam.setName("John");
			exam.setQuestions(4085);
			exam.setDuration(3262);
			exam.setPrice(28.92);
			exam
					.setScoringStrategy(com.oreon.kgauge.domain.ScoringType.ScoreForAllAnswers);
			exam.setExamStatus(com.oreon.kgauge.domain.ExamStatus.INCOMPLETE);

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

			exam.setDescription("John");
			exam.setName("Eric");
			exam.setQuestions(9678);
			exam.setDuration(4774);
			exam.setPrice(35.26);
			exam
					.setScoringStrategy(com.oreon.kgauge.domain.ScoringType.ScoreOnlyForCorrectAnswers);
			exam.setExamStatus(com.oreon.kgauge.domain.ExamStatus.INACTIVE);

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

			exam.setDescription("Wilson");
			exam.setName("delta");
			exam.setQuestions(5858);
			exam.setDuration(8163);
			exam.setPrice(62.36);
			exam
					.setScoringStrategy(com.oreon.kgauge.domain.ScoringType.ScoreOnlyForCorrectAnswers);
			exam.setExamStatus(com.oreon.kgauge.domain.ExamStatus.ACTIVE);

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

		exam.setDescription((String) RandomValueGeneratorFactory
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
