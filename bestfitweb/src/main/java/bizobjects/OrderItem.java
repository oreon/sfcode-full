package bizobjects;

import javax.persistence.*;


@Entity
public class OrderItem extends org.witchcraft.model.support.BusinessEntity
    implements java.io.Serializable {
    private double salePrice;
    private int quantity = 1;
    private bizobjects.Product product;

    public double getSalePrice() {
        return this.salePrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(bizobjects.Product product) {
        this.product = product;
    }

    @ManyToOne
    public bizobjects.Product getProduct() {
        return this.product;
    }
}
