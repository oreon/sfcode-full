package com.oreon.inventory.websvc.inventory;

import javax.jws.WebService;
import com.oreon.inventory.inventory.ProductQuantity;
import java.util.List;

@WebService
public interface ProductQuantityWebService {

	public ProductQuantity loadById(Long id);

	public List<ProductQuantity> findByExample(
			ProductQuantity exampleProductQuantity);

	public void save(ProductQuantity productQuantity);

}
