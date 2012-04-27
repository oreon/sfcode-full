package com.wc.shopper.domain;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Set;
import java.util.HashSet;
import com.wc.shopper.domain.ProductOrder;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import com.wc.shopper.domain.Address;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import com.wc.shopper.domain.Profile;
import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement
@Entity
public class Customer implements java.io.Serializable {

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
			return id.equals(((Customer) that).id);
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

	@Column
	private String firstName;

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	@Column
	private String lastName;

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	private @Temporal(TemporalType.DATE)
	Date birthDate;

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(final Date birthDate) {
		this.birthDate = birthDate;
	}

	private @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	Set<ProductOrder> orders = new HashSet<ProductOrder>();

	public Set<ProductOrder> getOrders() {
		return this.orders;
	}

	public void setOrders(final Set<ProductOrder> orders) {
		this.orders = orders;
	}

	@ManyToMany
	private Set<Address> addresses = new HashSet<Address>();

	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(final Set<Address> addresses) {
		this.addresses = addresses;
	}

	private @OneToOne(cascade = CascadeType.ALL)
	Profile profile;

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(final Profile profile) {
		this.profile = profile;
	}

	public String toString() {
		String result = "";
		if (firstName != null && !firstName.trim().isEmpty())
			result += firstName;
		if (lastName != null && !lastName.trim().isEmpty())
			result += " " + lastName;
		return result;
	}

	public void newProfile() {
		this.profile = new Profile();
	}
}