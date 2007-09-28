package bizobjects;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.AbstractTestDataFactory;

import bizobjects.service.ProductService;

public class ProductTestDataFactory extends AbstractTestDataFactory {

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

			product.setName("alpha");
			product.setBrand("gamma");
			product.setListPrice(2.454532713340274);

			register(product);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public Product createProductTwo() {
		Product product = new Product();

		try {

			product.setName("delta");
			product.setBrand("gamma");
			product.setListPrice(2.454532713340274);

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
			product.setBrand("gamma");
			product.setListPrice(2.454532713340274);

			register(product);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public Product createProductFour() {
		Product product = new Product();

		try {

			product.setName("epsilon");
			product.setBrand("alpha");
			product.setListPrice(2.454532713340274);

			register(product);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public Product createProductFive() {
		Product product = new Product();

		try {

			product.setName("delta");
			product.setBrand("delta");
			product.setListPrice(2.454532713340274);

			register(product);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return product;
	}

	public Product loadProduct() {
		List<Product> products = productService.loadAll();

		if (products.isEmpty()) {
			persistAll();
			products = productService.loadAll();
		}

		return products.get(new Random().nextInt(products.size()));
	}

	List<Product> getAllAsList() {

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
		if (!isPersistable())
			return;

		for (Product product : products) {
			productService.save(product);
		}
	}

	/** Will return a random number of PlacedOrders
	 * @return
	 */
	List<Product> getFew() {
		List<Product> all = getAllAsList();
		int numToChoose = new Random(1212343).nextInt(all.size() - 1) + 1;

		List allClone = new ArrayList<Product>();
		List returnList = new ArrayList<Product>();

		allClone.addAll(all);

		while (returnList.size() < numToChoose) {
			int indexToAdd = new Random(1212343).nextInt(allClone.size());
			returnList.add(allClone.get(indexToAdd));
			allClone.remove(numToChoose);
		}

		return returnList;
	}

}
