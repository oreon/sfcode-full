package com.oreon.inventory.websvc.inventory;

import javax.jws.WebService;
import com.oreon.inventory.inventory.Supplier;
import java.util.List;

@WebService
public interface SupplierWebService {

	public Supplier loadById(Long id);

	public List<Supplier> findByExample(Supplier exampleSupplier);

	public void save(Supplier supplier);

}
