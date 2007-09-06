package bizobjects;

import javax.persistence.*;


/*Represents a customer - customer can log in and place orders.*/
@Entity
public class Customer extends Person implements java.io.Serializable, User {
    private String remarks;
    private java.util.Set<bizobjects.PlacedOrder> orders = new java.util.HashSet<bizobjects.PlacedOrder>();

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

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    //Implementing interface User
    @Transient
    public String getUserId() {
        return null;

        //should return String
    }

    @Transient
    public String getPassword() {
        return null;

        //should return String
    }

    @Transient
    public String getRole() {
        return null;

        //should return String
    }

    //*****Done Implementing interface User ****
}
