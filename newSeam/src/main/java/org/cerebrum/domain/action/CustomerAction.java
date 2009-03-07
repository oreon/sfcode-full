package org.cerebrum.domain.action;

import org.cerebrum.domain.Customer;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
import org.jboss.seam.ScopeType;
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
	public void findRecords() {
		customerList = entityManager.createQuery(
				"select customer from Customer customer order by customer.id")
				.getResultList();
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

}
