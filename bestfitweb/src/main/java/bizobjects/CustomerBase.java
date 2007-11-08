
/**
 * This is generated code - to edit code or override methods use - Customer class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package bizobjects;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="Customer",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
/*Represents a customer - customer can log in and place orders.*/
public abstract class CustomerBase extends Person
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String remarks;

	@Column(nullable = true, unique = false)
	public String getRemarks() {

		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	private usermanagement.User userAccount = new usermanagement.User();

	private java.util.Set<bizobjects.PlacedOrder> orders = new java.util.HashSet<bizobjects.PlacedOrder>();

	public void setUserAccount(usermanagement.User userAccount) {
		this.userAccount = userAccount;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userAccount_ID", nullable = false)
	public usermanagement.User getUserAccount() {
		return this.userAccount;
	}

	public void addOrder(bizobjects.PlacedOrder orders) {

		orders.setCustomer(customerInstance());

		this.orders.add(orders);
	}

	public void removeOrder(bizobjects.PlacedOrder orders) {
		this.orders.remove(orders);
	}

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public java.util.Set<bizobjects.PlacedOrder> getOrders() {
		return this.orders;
	}

	public void setOrders(java.util.Set<bizobjects.PlacedOrder> orders) {
		this.orders = orders;
	}

	@Transient
	public java.util.Iterator<bizobjects.PlacedOrder> getOrdersIterator() {
		return this.orders.iterator();
	}

	/** Method size on the set doesn't work with technologies requiring 
	 *  java beans get/set  interface so we provide a getter method 
	 * @return
	 */
	@Transient
	public int getOrdersCount() {
		return this.orders.size();
	}

	public abstract Customer customerInstance();

	@Transient
	public String getDisplayName() {
		return lastName + ", " + firstName + "";
	}

}
