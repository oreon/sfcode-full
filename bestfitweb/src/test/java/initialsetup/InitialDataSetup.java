package initialsetup;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import org.witchcraft.model.support.TestDataFactory;

import bizobjects.CustomerTestDataFactory;

import bizobjects.EmployeeTestDataFactory;

import bizobjects.PlacedOrderTestDataFactory;

import bizobjects.ProductTestDataFactory;

import bizobjects.OrderItemTestDataFactory;

import usermanagement.UserTestDataFactory;

import usermanagement.AuthorityTestDataFactory;

/** 
 * @author jsingh
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

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.persistAll();

		TestDataFactory productTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("productTestDataFactory");

		productTestDataFactory.persistAll();

		TestDataFactory orderItemTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("orderItemTestDataFactory");

		orderItemTestDataFactory.persistAll();

		TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("userTestDataFactory");

		userTestDataFactory.persistAll();

		TestDataFactory authorityTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("authorityTestDataFactory");

		authorityTestDataFactory.persistAll();

	}
}
