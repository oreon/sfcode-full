package com.shan.customermgt.domain.action;

import java.util.List;

import javax.persistence.NoResultException;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.shan.customermgt.domain.Customer;

public class CustomerActionBase extends BaseAction<Customer> implements
		java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	//@DataModelSelection
	private Customer customer;

	@DataModel
	private List<Customer> customerList22;

	

	@Factory("customerList")
	@Observer("archivedCustomer")
	public void findRecords() {
		search();
	}

	public Customer getEntity() {
		return customer;
	}

	@Override
	public void setEntity(Customer t) {
		this.customer = t;
	}

	@Override
	public void setEntityList(List<Customer> list) {
		this.customerList22 = list;
	}

	public void updateAssociations() {

		com.shan.customermgt.domain.Account account = (com.shan.customermgt.domain.Account) Component
				.getInstance("account");
		account.setCustomer(customer);
		events.raiseTransactionSuccessEvent("archivedAccount");

	}

	public List<Customer> getEntityList() {
		if (customerList22 == null) {
			findRecords();
		}
		return customerList22;
	}
	
	//@Factory("customer")
	

}
