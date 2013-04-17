
	
package com.oreon.cerebrum.web.action.billing;
	

import org.jboss.seam.annotations.Name;

import com.oreon.cerebrum.billing.InvoiceItem;
import com.oreon.cerebrum.billing.Service;

	
//@Scope(ScopeType.CONVERSATION)
@Name("invoiceAction")
public class InvoiceAction extends InvoiceActionBase implements java.io.Serializable{
	
	
	public void applyPrice(InvoiceItem item){
		item.setAppliedPrice(item.getService().getPrice());	
	}
}
	