package bizobjects.dao;

import bizobjects.Product;

import org.springframework.orm.jpa.support.JpaDaoSupport;

import java.util.List;


public interface ProductDao {
    public Product save(Product product);

    public void delete(Product product);

    public Product load(Long id);

    public List<Product> loadAll();

    public List<Product> searchByExample(Product product);
}
