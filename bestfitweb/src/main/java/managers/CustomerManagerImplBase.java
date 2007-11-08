
/**
 * This is generated code - to edit code or override methods use - CustomerManagerImpl class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package managers;

import java.util.Date;
import org.apache.log4j.Logger;

public abstract class CustomerManagerImplBase
		implements
			java.io.Serializable,
			CustomerManager {
	private static final Logger log = Logger
			.getLogger(CustomerManagerImplBase.class);

	private static final long serialVersionUID = 1L;

	//Implementing interface CustomerManager
	public bizobjects.Customer addCustomer(bizobjects.Customer customer) {
		return null;
		//should return Customer
	}
	//*****Done Implementing interface CustomerManager ****

	public abstract CustomerManagerImpl customerManagerImplInstance();

}
