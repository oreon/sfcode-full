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

import com.oreon.kgauge.service.SectionService;

@Transactional
public class SectionTestDataFactory extends AbstractTestDataFactory<Section> {

	private List<Section> sections = new ArrayList<Section>();

	private static final Logger logger = Logger
			.getLogger(SectionTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	SectionService sectionService;

	public SectionService getSectionService() {
		return sectionService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	public void register(Section section) {
		sections.add(section);
	}

	public Section createSectionOne() {
		Section section = new Section();

		try {

			section.setName("zeta");

			TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examTestDataFactory");

			section.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
					.loadOneRecord());

			register(section);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return section;
	}

	public Section createSectionTwo() {
		Section section = new Section();

		try {

			section.setName("beta");

			TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examTestDataFactory");

			section.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
					.loadOneRecord());

			register(section);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return section;
	}

	public Section createSectionThree() {
		Section section = new Section();

		try {

			section.setName("gamma");

			TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examTestDataFactory");

			section.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
					.loadOneRecord());

			register(section);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return section;
	}

	public Section createSectionFour() {
		Section section = new Section();

		try {

			section.setName("alpha");

			TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examTestDataFactory");

			section.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
					.loadOneRecord());

			register(section);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return section;
	}

	public Section createSectionFive() {
		Section section = new Section();

		try {

			section.setName("Malissa");

			TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("examTestDataFactory");

			section.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
					.loadOneRecord());

			register(section);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return section;
	}

	public Section loadOneRecord() {
		List<Section> sections = sectionService.loadAll();

		if (sections.isEmpty()) {
			persistAll();
			sections = sectionService.loadAll();
		}

		return sections.get(new Random().nextInt(sections.size()));
	}

	public List<Section> getAllAsList() {

		if (sections.isEmpty()) {

			createSectionOne();
			createSectionTwo();
			createSectionThree();
			createSectionFour();
			createSectionFive();

		}

		return sections;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Section section : sections) {
			try {
				sectionService.save(section);
			} catch (BusinessException be) {
				logger.warn(" Section " + section.getDisplayName()
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
			Section section = createRandomSection();
			sectionService.save(section);
		}
	}

	public Section createRandomSection() {
		Section section = new Section();

		section.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));

		TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("examTestDataFactory");

		section.setExam((com.oreon.kgauge.domain.Exam) examTestDataFactory
				.loadOneRecord());

		return section;
	}

}
