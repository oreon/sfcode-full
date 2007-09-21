package bizobjects.dao.impl;

import bizobjects.Product;

import bizobjects.dao.ProductDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import java.util.List;


public class ProductDaoImpl extends JpaDaoSupport implements ProductDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    /**
    * This method saves or updates the given entity based upon whether the id is null
    */
    public Product save(Product product) {
        if (product.getId() == null) {
            getJpaTemplate().persist(product);
        } else {
            getJpaTemplate().merge(product);
        }

        return product;
    }

    public void delete(Product product) {
        getJpaTemplate().remove(product);
    }

    public Product load(Long id) {
        return getJpaTemplate().find(Product.class, id);
    }

    /*
         * loads all records for this entity
         */
    public List<Product> loadAll() {
        return getJpaTemplate().find("select product from Product product");
    }

    //// FINDERS ///// 
    public List<Product> searchByExample(Product product) {
        Session session = hibernateTemplate.getSessionFactory().openSession();

        try {
            Transaction tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Product.class)
                                       .add(Example.create(product)
                                                   .enableLike(MatchMode.START)
                                                   .ignoreCase().excludeZeroes()
                                                   .excludeProperty("dateModified")
                                                   .excludeProperty("id")
                                                   .excludeProperty("dateCreated"));

            List<Product> entities = criteria.list();

            tx.commit();

            return entities;
        } catch (Throwable t) {
            //TODO : Log error 
            return null;
        } finally {
            session.close();
        }
    }
}
