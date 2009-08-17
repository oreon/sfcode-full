package com.shan.customermgt.domain.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.witchcraft.users.User;

import com.shan.customermgt.domain.Account;
import com.shan.customermgt.domain.Customer;

@Name("customerHome")
public class CustomerHome extends EntityHome<Customer> {

	@In(create = true)
	UserHome userHome;

	public void setCustomerId(Long id) {
		setId(id);
	}

	public Long getCustomerId() {
		return (Long) getId();
	}

	@Override
	protected Customer createInstance() {
		Customer customer = new Customer();
		return customer;
	}

	public void wire() {
		getInstance();
		User user = userHome.getDefinedInstance();
		/*
		if (user != null) {
			getInstance().setUser(user);
		}*/
	}

	public boolean isWired() {
		return true;
	}

	public Customer getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Account> getAccounts() {
		return getInstance() == null ? null : new ArrayList<Account>(
				getInstance().getAccounts());
	}

}
