package org.cerebrum.domain.action;

import org.cerebrum.domain.Item;
import org.cerebrum.domain.Order;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.apache.commons.collections.OrderedIterator;
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
public class OrderAction extends BaseAction<Order>
		implements
			java.io.Serializable {

	@In(create = true)
	@Out(required = false)
	@DataModelSelection
	private Order order;
	
	private List<Item> listItems ;
	
	@DataModel
	private List<Order> orderList;
	
	void initLists(){
		listItems = new ArrayList<Item>();
		if(order.getItems().isEmpty())
			addItem();
		else
			listItems.addAll(order.getItems());
	}
	

	public List<Item> getListItems() {
		if(listItems == null){
			initLists();
		}
		return listItems;
	}

	public void setListItems(List<Item> listItems) {
		this.listItems = listItems;
	}
	
	public void deleteItem(Item item){
		listItems.remove(item);
	}
	
	@Begin(join=true)
	public void addItem(){
		Item item = new Item();
		item.setOrder(order);
		listItems.add(item);
		System.out.println(listItems.size());
	}

	@Factory("orderList")
	public void findRecords() {
		orderList = entityManager.createQuery(
				"select order from Order order order by order.id")
				.getResultList();
		
	}
	
	
	
	@Override
	@End
	public String save() {
		order.getItems().clear();
		order.getItems().addAll(listItems);
		
		
		return super.save();
		
	}

	public Order getEntity() {
		return order;
	}

	@Override
	public void setEntity(Order t) {
		this.order = t;
	}

	@Override
	public void setEntityList(List<Order> list) {
		this.orderList = list;
	}

}
