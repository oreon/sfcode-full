package com.oreon.inventory.websvc.inventory;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.inventory.inventory.Product;

@WebService(endpointInterface = "com.oreon.inventory.websvc.inventory.ProductWebService", serviceName = "ProductWebService")
@Name("productWebService")
public class ProductWebServiceImpl implements ProductWebService {

	@In(create = true)
	com.oreon.inventory.web.action.inventory.ProductAction productAction;

	public Product loadById(Long id) {
		return productAction.loadFromId(id);
	}

	public List<Product> findByExample(Product exampleProduct) {
		return productAction.search(exampleProduct);
	}

	public void save(Product product) {
		productAction.persist(product);
	}

}
