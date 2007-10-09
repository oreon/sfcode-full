package initialsetup;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.TestDataFactory;

import bizobjects.CustomerTestDataFactory;

import bizobjects.EmployeeTestDataFactory;

import bizobjects.PlacedOrderTestDataFactory;

import bizobjects.ProductTestDataFactory;

import bizobjects.OrderItemTestDataFactory;

import bizobjects.RegisteredUserTestDataFactory;

import usermanagement.UserTestDataFactory;

import usermanagement.AuthorityTestDataFactory;

import com.publicfountain.domain.TopicTestDataFactory;

import com.publicfountain.domain.CategoryTestDataFactory;

import com.publicfountain.domain.CommentTestDataFactory;

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

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.persistAll();

		TestDataFactory productTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("productTestDataFactory");

		productTestDataFactory.persistAll();

		TestDataFactory orderItemTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("orderItemTestDataFactory");

		orderItemTestDataFactory.persistAll();

		TestDataFactory registeredUserTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("registeredUserTestDataFactory");

		registeredUserTestDataFactory.persistAll();

		TestDataFactory userTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("userTestDataFactory");

		userTestDataFactory.persistAll();

		TestDataFactory authorityTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("authorityTestDataFactory");

		authorityTestDataFactory.persistAll();

		TestDataFactory topicTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("topicTestDataFactory");

		topicTestDataFactory.persistAll();

		TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		categoryTestDataFactory.persistAll();

		TestDataFactory commentTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("commentTestDataFactory");

		commentTestDataFactory.persistAll();

	}
}
