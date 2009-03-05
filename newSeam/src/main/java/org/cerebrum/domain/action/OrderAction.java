package org.cerebrum.domain.action;

import org.cerebrum.domain.Order;
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
@Name("orderAction")
public class OrderAction extends BaseAction implements java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Order order;

	@DataModel
	private List<Order> orderList;

	@Factory("orderList")
	public void findRecords() {
		orderList = entityManager.createQuery(
				"select order from Order order order by order.id")
				.getResultList();
	}

	@Begin
	public String select(Order order) {
		this.order = entityManager.merge(order);
		log.info("User selected Order: #{order.displayName} #{order.id} ");
		return "select";
	}

	@End
	public String save() {
		facesMessages.add("Successfully saved  #{order.displayName}");
		entityManager.persist(order);
		return "save";
	}

	@End
	public String cancel() {
		return "cancel";
		//System.out.println("in cancel");
	}

}
