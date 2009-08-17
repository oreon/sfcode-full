package com.shan.customermgt.domain.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.witchcraft.users.User;

import com.shan.customermgt.domain.Account;
import com.shan.customermgt.domain.Customer;
import com.shan.customermgt.domain.CustomerOrder;

@Name("accountHome")
public class AccountHome extends EntityHome<Account> {

	@In(create = true)
	CustomerHome customerHome;
	@In(create = true)
	UserHome userHome;

	public void setAccountId(Long id) {
		setId(id);
	}

	public Long getAccountId() {
		return (Long) getId();
	}

	@Override
	protected Account createInstance() {
		Account account = new Account();
		return account;
	}

	public void wire() {
		getInstance();
		Customer customer = customerHome.getDefinedInstance();
		if (customer != null) {
			getInstance().setCustomer(customer);
		}
		User user = userHome.getDefinedInstance();
		/*
		if (user != null) {
			getInstance().setUser(user);
		}*/
	}

	public boolean isWired() {
		if (getInstance().getCustomer() == null)
			return false;
		return true;
	}

	public Account getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/*
	public List<CustomerOrder> getCustomerOrders() {
		return getInstance() == null ? null : new ArrayList<CustomerOrder>(
				getInstance().getCustomerOrders());
	}*/

}
