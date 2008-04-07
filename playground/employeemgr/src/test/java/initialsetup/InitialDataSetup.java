package initialsetup;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.TestDataFactory;

import com.mycomapny.employeemgr.domain.EmployeeTestDataFactory;

import com.mycomapny.employeemgr.domain.TaskTestDataFactory;

/** 
 * This class populates the database with some initial data
 *
 */
public class InitialDataSetup {

	public static void main(String args[]) {

		TestDataFactory employeeTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("employeeTestDataFactory");

		employeeTestDataFactory.persistAll();

		TestDataFactory taskTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("taskTestDataFactory");

		taskTestDataFactory.persistAll();

	}
}
