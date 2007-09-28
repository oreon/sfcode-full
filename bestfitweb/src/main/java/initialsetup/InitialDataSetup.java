package initialsetup;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

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

		CustomerTestDataFactory customerTestDataFactory = (CustomerTestDataFactory) BeanHelper
				.getBean("customerTestDataFactory");

		customerTestDataFactory.persistAll();

		EmployeeTestDataFactory employeeTestDataFactory = (EmployeeTestDataFactory) BeanHelper
				.getBean("employeeTestDataFactory");

		employeeTestDataFactory.persistAll();

		PlacedOrderTestDataFactory placedOrderTestDataFactory = (PlacedOrderTestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.persistAll();

		ProductTestDataFactory productTestDataFactory = (ProductTestDataFactory) BeanHelper
				.getBean("productTestDataFactory");

		productTestDataFactory.persistAll();

		OrderItemTestDataFactory orderItemTestDataFactory = (OrderItemTestDataFactory) BeanHelper
				.getBean("orderItemTestDataFactory");

		orderItemTestDataFactory.persistAll();

		UserTestDataFactory userTestDataFactory = (UserTestDataFactory) BeanHelper
				.getBean("userTestDataFactory");

		userTestDataFactory.persistAll();

		AuthorityTestDataFactory authorityTestDataFactory = (AuthorityTestDataFactory) BeanHelper
				.getBean("authorityTestDataFactory");

		authorityTestDataFactory.persistAll();

	}
}
