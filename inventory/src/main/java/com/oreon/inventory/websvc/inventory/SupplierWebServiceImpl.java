package com.oreon.inventory.websvc.inventory;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.inventory.inventory.Supplier;

@WebService(endpointInterface = "com.oreon.inventory.websvc.inventory.SupplierWebService", serviceName = "SupplierWebService")
@Name("supplierWebService")
public class SupplierWebServiceImpl implements SupplierWebService {

	@In(create = true)
	com.oreon.inventory.web.action.inventory.SupplierAction supplierAction;

	public Supplier loadById(Long id) {
		return supplierAction.loadFromId(id);
	}

	public List<Supplier> findByExample(Supplier exampleSupplier) {
		return supplierAction.search(exampleSupplier);
	}

	public void save(Supplier supplier) {
		supplierAction.persist(supplier);
	}

}
