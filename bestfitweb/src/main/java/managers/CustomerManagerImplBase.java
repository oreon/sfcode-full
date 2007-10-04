package managers;

import javax.persistence.*;

public abstract class CustomerManagerImplBase
		implements
			java.io.Serializable,
			CustomerManager {

	private static final long serialVersionUID = 1L;

	//Implementing interface CustomerManager
	public bizobjects.Customer addCustomer(bizobjects.Customer customer) {
		return null;
		//should return Customer
	}
	//*****Done Implementing interface CustomerManager ****

	public abstract CustomerManagerImpl customerManagerImplInstance();

}
