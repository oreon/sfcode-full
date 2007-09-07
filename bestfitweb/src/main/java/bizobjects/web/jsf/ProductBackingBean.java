package bizobjects.web.jsf;

import bizobjects.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductBackingBean {
    private Product product = new Product();

    public Product getProduct() {
        return product;
    }

    public void set(Product product) {
        this.product = product;
    }

    /**Write values to the database
    * @return - a list of
    */
    public String update() {
        return "success";
    }

    /**Get a list of all products
    * @return - a list of products
    */
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<Product>();

        return products;
    }
}
