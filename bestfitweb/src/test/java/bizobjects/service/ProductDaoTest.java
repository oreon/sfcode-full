package bizobjects.service;

import bizobjects.Product;
import org.springframework.test.jpa.AbstractJpaTests;
import java.util.List;

import org.witchcraft.model.support.testing.TestDataFactory;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import java.text.SimpleDateFormat;

import javax.persistence.PersistenceException;
import org.hibernate.PropertyValueException;

public class ProductDaoTest extends AbstractJpaTests {

	protected Product productInstance = new Product();

	protected ProductService productService;

	protected boolean bTest = true;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	protected TestDataFactory productTestDataFactory = (TestDataFactory) BeanHelper
			.getBean("productTestDataFactory");

	@Override
	protected String[] getConfigLocations() {
		return new String[]{"classpath:/applicationContext.xml",
				"classpath:/testDataFactories.xml"};
	}

	@Override
	protected void runTest() throws Throwable {
		if (!bTest)
			return;
		super.runTest();
	}

	/**
	 * Do the setup before the test in this method
	 **/
	protected void onSetUpInTransaction() throws Exception {
		try {

			productInstance.setName("beta");
			productInstance.setBrand("Lavendar");
			productInstance.setListPrice(24.0);

			productService.save(productInstance);
		} catch (PersistenceException pe) {
			//if this instance can't be created due to back references e.g an orderItem needs an Order - 
			// - we will simply skip generated tests.
			if (pe.getCause() instanceof PropertyValueException
					&& pe.getMessage().contains("Backref")) {
				bTest = false;
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	//test saving a new record and updating an existing record;
	public void testSave() {

		try {
			Product product = new Product();

			try {

				product.setName("epsilon");
				product.setBrand("alpha");
				product.setListPrice(79.88);

			} catch (Exception ex) {
				ex.printStackTrace();
			}

			productService.save(product);
			assertNotNull(product.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testEdit() {

		try {
			//test saving a new record and updating an existing record;
			Product product = (Product) productTestDataFactory.loadOneRecord();

			product.setName("Mark");
			product.setBrand("beta");
			product.setListPrice(14.77);

			productService.save(product);

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testDelete() {
		//return false;
	}

	public void testLoad() {

		try {
			Product product = productService.load(productInstance.getId());
			assertNotNull(product.getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public void testSearchByExample() {
		try {
			List<Product> products = productService
					.searchByExample(productInstance);
			assertTrue(!products.isEmpty());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
