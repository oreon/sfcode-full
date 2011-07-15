package com.oreon.inventory.websvc.inventory;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.inventory.inventory.Purchase;

@WebService(endpointInterface = "com.oreon.inventory.websvc.inventory.PurchaseWebService", serviceName = "PurchaseWebService")
@Name("purchaseWebService")
public class PurchaseWebServiceImpl implements PurchaseWebService {

	@In(create = true)
	com.oreon.inventory.web.action.inventory.PurchaseAction purchaseAction;

	public Purchase loadById(Long id) {
		return purchaseAction.loadFromId(id);
	}

	public List<Purchase> findByExample(Purchase examplePurchase) {
		return purchaseAction.search(examplePurchase);
	}

	public void save(Purchase purchase) {
		purchaseAction.persist(purchase);
	}

}
