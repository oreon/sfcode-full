package com.nas.recovery.testdata;

import java.util.List;
import java.util.ArrayList;
import org.witchcraft.action.test.AbstractTestDataFactory;

public class TestDataGenerator {

	public static List<AbstractTestDataFactory> listDataFactory = new ArrayList<AbstractTestDataFactory>();

	static {

		listDataFactory
				.add(new org.wc.trackrite.domain.testdata.EmployeeTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.domain.testdata.DepartmentTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.domain.testdata.EndUserTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.issues.testdata.ProjectTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.issues.testdata.IssueTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.issues.testdata.ModuleTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.users.testdata.UserTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.users.testdata.RoleTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.timetrack.testdata.TimeTrackingEntryTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.timetrack.testdata.WorkDayTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.onepack.testdata.ExamTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.onepack.testdata.QuestionTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.onepack.testdata.ChoiceTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.onepack.testdata.CandidateTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.onepack.testdata.ExamInstanceTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.onepack.testdata.AnswerTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.schedule.testdata.ScheduleTestDataFactory());

		listDataFactory
				.add(new org.wc.trackrite.schedule.testdata.ScheduleItemTestDataFactory());

	}

	public void persist() {
		for (AbstractTestDataFactory dataFactory : listDataFactory) {
			dataFactory.persistAll();
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new TestDataGenerator().persist();
	}

}
