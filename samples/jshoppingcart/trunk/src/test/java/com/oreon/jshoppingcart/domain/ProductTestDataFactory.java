package com.oreon.jshoppingcart.domain;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.springframework.transaction.annotation.Transactional;

import com.oreon.jshoppingcart.service.ProductService;

@Transactional
public class ProductTestDataFactory extends AbstractTestDataFactory<Product> {

	List<Product> products = new ArrayList<Product>();

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void register(Product product) {
		products.add(product);
	}

	public Product createProductOne() {
		Product product = new Product();

		try {

			product.setName("John");
			product.setPrice(13.79);

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			product
					.setCategory((com.oreon.jshoppingcart.domain.Category) categoryTestDataFactory
							.loadOneRecord());

			register(product);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public Product createProductTwo() {
		Product product = new Product();

		try {

			product.setName("John");
			product.setPrice(74.86);

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			product
					.setCategory((com.oreon.jshoppingcart.domain.Category) categoryTestDataFactory
							.loadOneRecord());

			register(product);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public Product createProductThree() {
		Product product = new Product();

		try {

			product.setName("pi");
			product.setPrice(12.46);

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			product
					.setCategory((com.oreon.jshoppingcart.domain.Category) categoryTestDataFactory
							.loadOneRecord());

			register(product);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public Product createProductFour() {
		Product product = new Product();

		try {

			product.setName("pi");
			product.setPrice(55.9);

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			product
					.setCategory((com.oreon.jshoppingcart.domain.Category) categoryTestDataFactory
							.loadOneRecord());

			register(product);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public Product createProductFive() {
		Product product = new Product();

		try {

			product.setName("theta");
			product.setPrice(62.25);

			TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
					.getBean("categoryTestDataFactory");

			product
					.setCategory((com.oreon.jshoppingcart.domain.Category) categoryTestDataFactory
							.loadOneRecord());

			register(product);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public Product loadOneRecord() {
		List<Product> products = productService.loadAll();

		if (products.isEmpty()) {
			persistAll();
			products = productService.loadAll();
		}

		return products.get(new Random().nextInt(products.size()));
	}

	public List<Product> getAllAsList() {

		if (products.isEmpty()) {

			createProductOne();
			createProductTwo();
			createProductThree();
			createProductFour();
			createProductFive();

		}

		return products;
	}

	public void persistAll() {
		if (!isPersistable() || alreadyPersisted)
			return;

		getAllAsList();

		for (Product product : products) {
			productService.save(product);
		}

		alreadyPersisted = true;
	}

	/** Execute this method to manually generate additional orders
	 * @param args
	 */
	public static void main(String args[]) {

		int recordsTocreate = 30;

		TestDataFactory placedOrderTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("placedOrderTestDataFactory");

		placedOrderTestDataFactory.createAndSaveRecords(recordsTocreate);
	}

	public void createAndSaveRecords(int recordsTocreate) {
		for (int i = 0; i < recordsTocreate; i++) {
			Product product = createRandomProduct();
			productService.save(product);
		}
	}

	public Product createRandomProduct() {
		Product product = new Product();

		product.setName((String) RandomValueGeneratorFactory
				.createInstance("String"));
		product.setPrice((Double) RandomValueGeneratorFactory
				.createInstance("Double"));

		TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		product
				.setCategory((com.oreon.jshoppingcart.domain.Category) categoryTestDataFactory
						.loadOneRecord());

		return product;
	}

}
