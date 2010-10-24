package org.wc.trackrite.issues.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.action.test.AbstractTestDataFactory;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class ProjectTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.issues.Project> {

	private List<org.wc.trackrite.issues.Project> projects = new ArrayList<org.wc.trackrite.issues.Project>();

	private static final Logger logger = Logger
			.getLogger(ProjectTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.issues.ProjectAction projectAction;

	// @In(create = true,  value="issueList")
	//com.nas.recovery.web.action.issues.IssueListQuery issueList;

	public void register(org.wc.trackrite.issues.Project project) {
		projects.add(project);
	}

	public org.wc.trackrite.issues.Project createProjectOne() {
		org.wc.trackrite.issues.Project project = new org.wc.trackrite.issues.Project();

		try {

			project.setName("epsilon");

			project.setDescription("beta");

			register(project);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return project;
	}

	public org.wc.trackrite.issues.Project createProjectTwo() {
		org.wc.trackrite.issues.Project project = new org.wc.trackrite.issues.Project();

		try {

			project.setName("alpha");

			project.setDescription("zeta");

			register(project);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return project;
	}

	public org.wc.trackrite.issues.Project createProjectThree() {
		org.wc.trackrite.issues.Project project = new org.wc.trackrite.issues.Project();

		try {

			project.setName("beta");

			project.setDescription("pi");

			register(project);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return project;
	}

	public org.wc.trackrite.issues.Project createProjectFour() {
		org.wc.trackrite.issues.Project project = new org.wc.trackrite.issues.Project();

		try {

			project.setName("epsilon");

			project.setDescription("pi");

			register(project);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return project;
	}

	public org.wc.trackrite.issues.Project createProjectFive() {
		org.wc.trackrite.issues.Project project = new org.wc.trackrite.issues.Project();

		try {

			project.setName("beta");

			project.setDescription("Mark");

			register(project);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return project;
	}

	public org.wc.trackrite.issues.Project getRandomRecord() {

		if (projects.isEmpty()) {
			createAll();
		}

		return projects.get(new Random().nextInt(projects.size()));
	}

	public List<org.wc.trackrite.issues.Project> createAll() {
		createProjectOne();
		createProjectTwo();
		createProjectThree();
		createProjectFour();
		createProjectFive();

		return projects;
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.issues.Project project : projects) {
			persist(project);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new ProjectTestDataFactory().persistAll();
	}

}
