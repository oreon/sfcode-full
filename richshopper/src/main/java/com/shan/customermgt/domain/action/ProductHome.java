package com.shan.customermgt.domain.action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;
import org.witchcraft.users.User;

import com.shan.customermgt.domain.Product;

@Name("productHome")
public class ProductHome extends EntityHome<Product> {

	@In(create = true)
	UserHome userHome;

	public void setProductId(Long id) {
		setId(id);
	}

	public Long getProductId() {
		return (Long) getId();
	}

	@Override
	protected Product createInstance() {
		Product product = new Product();
		return product;
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

	public Product getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	

}
