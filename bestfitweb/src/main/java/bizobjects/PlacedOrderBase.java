package bizobjects;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="PlacedOrder",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class PlacedOrderBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String remarks;

	protected String paymentMethod;

	protected OrderStatus status = OrderStatus.NEW;

	protected double total;

	public String getRemarks() {
		return this.remarks;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public OrderStatus getStatus() {
		return this.status;
	}

	@Transient
	public double getTotal() {

		total = 0.0;
		for (OrderItem orderItem : orderItems)
			total += orderItem.getTotal();

		return this.total;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	private bizobjects.Customer customer;

	private java.util.Set<bizobjects.OrderItem> orderItems = new java.util.HashSet<bizobjects.OrderItem>();

	public void setCustomer(bizobjects.Customer customer) {
		this.customer = customer;
	}

	@ManyToOne
	@JoinColumn(name = "customer_ID", nullable = false)
	public bizobjects.Customer getCustomer() {
		return this.customer;
	}

	public void addOrderItems(bizobjects.OrderItem orderItems) {

		this.orderItems.add(orderItems);
	}

	public void removeOrderItems(bizobjects.OrderItem orderItems) {
		this.orderItems.remove(orderItems);
	}

	@OneToMany(cascade = CascadeType.ALL)
	@Cascade({org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	@JoinColumn(name = "order_ID", nullable = false)
	public java.util.Set<bizobjects.OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(java.util.Set<bizobjects.OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Transient
	public java.util.Iterator<bizobjects.OrderItem> getOrderItemsIterator() {
		return this.orderItems.iterator();
	}

	public abstract PlacedOrder placedOrderInstance();

	@Transient
	public String getDisplayName() {
		return customer + ":" + dateCreated + "";
	}

}
