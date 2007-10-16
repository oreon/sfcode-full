package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.testing.AbstractTestDataFactory;

import org.witchcraft.model.support.testing.TestDataFactory;

import org.springframework.transaction.annotation.Transactional;

import bizobjects.service.ProductService;

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

			product.setName("Mark");
			product.setBrand("pi");
			product.setListPrice(68.72);

			register(product);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public Product createProductTwo() {
		Product product = new Product();

		try {

			product.setName("Wilson");
			product.setBrand("Mark");
			product.setListPrice(36.82);

			register(product);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public Product createProductThree() {
		Product product = new Product();

		try {

			product.setName("delta");
			product.setBrand("Lavendar");
			product.setListPrice(5.59);

			register(product);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public Product createProductFour() {
		Product product = new Product();

		try {

			product.setName("Malissa");
			product.setBrand("delta");
			product.setListPrice(53.71);

			register(product);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public Product createProductFive() {
		Product product = new Product();

		try {

			product.setName("epsilon");
			product.setBrand("Eric");
			product.setListPrice(75.03);

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

}
