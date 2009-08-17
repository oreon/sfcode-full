package com.shan.customermgt.domain.action;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.witchcraft.users.User;

import com.shan.customermgt.domain.CustomerOrder;
import com.shan.customermgt.domain.OrderItem;
import com.shan.customermgt.domain.Product;

@Name("orderitemHome")
public class OrderitemHome extends EntityHome<OrderItem> {

	@In(create = true)
	CustomerOrderHome customerorderHome;
	
	@In(create = true)
	ProductHome productHome;
	
	@In(create = true)
	UserHome userHome;

	public void setOrderitemId(Long id) {
		setId(id);
	}

	public Long getOrderitemId() {
		return (Long) getId();
	}

	@Override
	protected OrderItem createInstance() {
		OrderItem orderitem = new OrderItem();
		return orderitem;
	}

	public void wire() {
		getInstance();
		CustomerOrder customerorder = customerorderHome.getDefinedInstance();
		if (customerorder != null) {
			getInstance().setCustomerOrder(customerorder);
		}
		Product product = productHome.getDefinedInstance();
		if (product != null) {
			getInstance().setProduct(product);
		}
		User user = userHome.getDefinedInstance();
		/*
		if (user != null) {
			getInstance().setUser(user);
		}*/
	}

	public boolean isWired() {
		if (getInstance().getCustomerOrder() == null)
			return false;
		if (getInstance().getProduct() == null)
			return false;
		return true;
	}

	public OrderItem getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
