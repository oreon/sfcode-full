package bizobjects.service;

import bizobjects.Product;

import org.springframework.test.jpa.AbstractJpaTests;

import java.util.List;


public class ProductDaoTest extends AbstractJpaTests {
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "classpath:/applicationContext.xml" };
    }

    /**
    * Do the setup before the test in this method
    **/
    protected void onSetUpInTransaction() throws Exception {
    }

    public void testSave() {
        //test saving a new record and updating an existing record;
    }

    public void testDelete() {
        //return false;
    }

    public void testLoad() {
        //return null;
    }

    public void testSearchByExample() {
        Product product = new Product();

        //product.setFirstName("Eri");
        List<Product> products = productService.searchByExample(product);
        //assertTrue(!products.isEmpty());
    }
}
