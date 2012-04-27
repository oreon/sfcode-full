package com.wc.shopper.domain;


import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import com.wc.shopper.domain.Customer;
import javax.persistence.ManyToOne;
import java.util.Set;
import java.util.HashSet;
import com.wc.shopper.domain.Item;
import javax.persistence.ManyToMany;
import com.wc.shopper.domain.Address;
import javax.xml.bind.annotation.XmlRootElement;@XmlRootElement @Entity public class ProductOrder implements java.io.Serializable {

	@Id
	private @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	Long id = null;
	@Version
	private @Column(name = "version")
	int version = 0;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	public String toString() {
		String result = "";
		if (id != null)
			result += id;
		return result;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		if (id != null) {
			return id.equals(((ProductOrder) that).id);
		}
		return super.equals(that);
	}

	@Override
	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		}
		return super.hashCode();
	}

	@ManyToOne
	private Customer customer;

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	@ManyToMany
	private Set<Item> items = new HashSet<Item>();

	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(final Set<Item> items) {
		this.items = items;
	}

	@ManyToOne
	private Address shippingAddress;

	public Address getShippingAddress() {
		return this.shippingAddress;
	}

	public void setShippingAddress(final Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	} }