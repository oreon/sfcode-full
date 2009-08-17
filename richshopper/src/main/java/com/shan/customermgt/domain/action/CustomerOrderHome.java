package com.shan.customermgt.domain.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.witchcraft.users.User;

import com.shan.customermgt.domain.Account;
import com.shan.customermgt.domain.CustomerOrder;
import com.shan.customermgt.domain.OrderItem;

@Name("customerorderHome")
public class CustomerOrderHome extends EntityHome<CustomerOrder> {

	@In(create = true)
	AccountHome accountHome;
	
	@In(create = true)
	UserHome userHome;

	public void setCustomerOrderId(Long id) {
		setId(id);
	}

	public Long getCustomerOrderId() {
		return (Long) getId();
	}

	@Override
	protected CustomerOrder createInstance() {
		CustomerOrder customerorder = new CustomerOrder();
		return customerorder;
	}

	public void wire() {
		getInstance();
		Account account = accountHome.getDefinedInstance();
		if (account != null) {
			getInstance().setAccount(account);
		}
		User user = userHome.getDefinedInstance();
		/*
		if (user != null) {
			getInstance().setUser(user);
		}*/
	}

	public boolean isWired() {
		if (getInstance().getAccount() == null)
			return false;
		return true;
	}

	public CustomerOrder getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<OrderItem> getOrderitems() {
		return getInstance() == null ? null : new ArrayList<OrderItem>(
				getInstance().getOrderItems());
	}

}
