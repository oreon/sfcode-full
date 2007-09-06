package bizobjects.web.jsf;

import bizobjects.Product;


public class ProductBackingBean {
    private Product product = new Product();

    public Product getProduct(Product product) {
        return product;
    }

    public void set(Product product) {
        this.product = product;
    }

    public String update() {
        return "success";
    }
}
