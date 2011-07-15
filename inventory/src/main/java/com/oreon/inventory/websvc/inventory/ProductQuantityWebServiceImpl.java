package com.oreon.inventory.websvc.inventory;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.inventory.inventory.ProductQuantity;

@WebService(endpointInterface = "com.oreon.inventory.websvc.inventory.ProductQuantityWebService", serviceName = "ProductQuantityWebService")
@Name("productQuantityWebService")
public class ProductQuantityWebServiceImpl implements ProductQuantityWebService {

	@In(create = true)
	com.oreon.inventory.web.action.inventory.ProductQuantityAction productQuantityAction;

	public ProductQuantity loadById(Long id) {
		return productQuantityAction.loadFromId(id);
	}

	public List<ProductQuantity> findByExample(
			ProductQuantity exampleProductQuantity) {
		return productQuantityAction.search(exampleProductQuantity);
	}

	public void save(ProductQuantity productQuantity) {
		productQuantityAction.persist(productQuantity);
	}

}
