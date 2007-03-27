package bizobjects;


//@entity
public class OrderItem {
    private double salePrice;
    private int quantity;
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

    public void setProduct(bizobjects.Product pProduct) {
        this.product = pProduct;
    }

    public bizobjects.Product getProduct() {
        return this.product;
    }
}
