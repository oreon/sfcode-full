package com.oreon.inventory.websvc.inventory;

import javax.jws.WebService;
import com.oreon.inventory.inventory.Product;
import java.util.List;

@WebService
public interface ProductWebService {

	public Product loadById(Long id);

	public List<Product> findByExample(Product exampleProduct);

	public void save(Product product);

}
