package com.shan.customermgt.domain.action;

import com.shan.customermgt.domain.Customer;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import org.witchcraft.seam.action.BaseAction;
import org.jboss.seam.annotations.Observer;

@Scope(ScopeType.CONVERSATION)
@Name("customerAction")
public class CustomerAction extends BaseAction<Customer>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Customer customer;

	@DataModel
	private List<Customer> customerList;

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
		this.customerList = list;
	}

	public void updateAssociations() {

		com.shan.customermgt.domain.Account account = (com.shan.customermgt.domain.Account) Component
				.getInstance("account");
		account.setCustomer(customer);
		events.raiseTransactionSuccessEvent("archivedAccount");

	}

	public List<Customer> getEntityList() {
		if (customerList == null) {
			findRecords();
		}
		return customerList;
	}

}
