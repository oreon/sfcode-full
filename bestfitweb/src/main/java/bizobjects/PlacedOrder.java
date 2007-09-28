package bizobjects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class /*0 */PlacedOrder
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String remarks;

	private String paymentMethod;

	private OrderStatus status = OrderStatus.NEW;

	public String getRemarks() {
		return this.remarks;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public OrderStatus getStatus() {
		return this.status;
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

	@OneToMany
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

}
