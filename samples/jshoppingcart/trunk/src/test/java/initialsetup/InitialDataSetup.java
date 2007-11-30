package initialsetup;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.TestDataFactory;

import com.oreon.jshoppingcart.domain.CustomerTestDataFactory;

import com.oreon.jshoppingcart.domain.EmployeeTestDataFactory;

import com.oreon.jshoppingcart.domain.CategoryTestDataFactory;

import com.oreon.jshoppingcart.domain.ProductTestDataFactory;

/** 
 * This class populates the database with some initial data
 *
 */
public class InitialDataSetup {

	public static void main(String args[]) {

		TestDataFactory customerTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("customerTestDataFactory");

		customerTestDataFactory.persistAll();

		TestDataFactory employeeTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("employeeTestDataFactory");

		employeeTestDataFactory.persistAll();

		TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		categoryTestDataFactory.persistAll();

		TestDataFactory productTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("productTestDataFactory");

		productTestDataFactory.persistAll();

	}
}
