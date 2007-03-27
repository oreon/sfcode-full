package bizobjects;


//@entity
public class Order {
    private Date created;
    private String paymentMethod;
    private String status;
    private java.util.Collection<bizobjects.OrderItem> orderItems = new java.util.HashSet<bizobjects.OrderItem>();
    private bizobjects.Customer customer;

    public Date getCreated() {
        return this.created;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public String getStatus() {
        return this.status;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCustomer(bizobjects.Customer pCustomer) {
        this.customer = pCustomer;
    }

    public bizobjects.Customer getCustomer() {
        return this.customer;
    }

    public void addOrderItems(bizobjects.OrderItem pOrderItems) {
        pOrderItems.setnull(this);
        this.orderItems.add(pOrderItems);
    }

    public void removeOrderItems(bizobjects.OrderItem pOrderItems) {
        this.orderItems.remove(pOrderItems);
    }

    public java.util.Collection<bizobjects.OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(
        java.util.Collection<bizobjects.OrderItem> pOrderItems) {
        this.orderItems = pOrderItems;
    }

    public java.util.Iterator<bizobjects.OrderItem> getOrderItemsIterator() {
        return this.orderItems.iterator();
    }
}
