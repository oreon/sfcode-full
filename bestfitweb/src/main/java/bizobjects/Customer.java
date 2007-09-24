package bizobjects;

import javax.persistence.*;


/*Represents a customer - customer can log in and place orders.*/
@Entity
public class Customer extends Person implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String remarks;
    private java.util.Set<bizobjects.PlacedOrder> orders = new java.util.HashSet<bizobjects.PlacedOrder>();

   
    private User userAccount;
    
    @OneToOne
    @JoinColumn(name = "user_ID", nullable = false)
    public User getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(User userAccount) {
		this.userAccount = userAccount;
	}

	@Column(nullable = true, unique = false)
    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void addOrders(bizobjects.PlacedOrder orders) {
        orders.setCustomer(this);
        this.orders.add(orders);
    }

    public void removeOrders(bizobjects.PlacedOrder orders) {
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

   
}
