package bizobjects;


//@entity
public class Customer extends Person {
    private String remarks;
    private java.util.Collection<bizobjects.Order> orders = new java.util.HashSet<bizobjects.Order>();

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void addOrders(bizobjects.Order pOrders) {
        pOrders.setCustomer(this);
        this.orders.add(pOrders);
    }

    public void removeOrders(bizobjects.Order pOrders) {
        this.orders.remove(pOrders);
    }

    public java.util.Collection<bizobjects.Order> getOrders() {
        return this.orders;
    }

    public void setOrders(java.util.Collection<bizobjects.Order> pOrders) {
        this.orders = pOrders;
    }

    public java.util.Iterator<bizobjects.Order> getOrdersIterator() {
        return this.orders.iterator();
    }
}
