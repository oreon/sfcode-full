package com.oreon.inventory.websvc.inventory;

import javax.jws.WebService;
import com.oreon.inventory.inventory.Purchase;
import java.util.List;

@WebService
public interface PurchaseWebService {

	public Purchase loadById(Long id);

	public List<Purchase> findByExample(Purchase examplePurchase);

	public void save(Purchase purchase);

}
