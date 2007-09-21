package bizobjects.dao.impl;

import bizobjects.OrderItem;

import bizobjects.dao.OrderItemDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import java.util.List;


public class OrderItemDaoImpl extends JpaDaoSupport implements OrderItemDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    /**
    * This method saves or updates the given entity based upon whether the id is null
    */
    public OrderItem save(OrderItem orderItem) {
        if (orderItem.getId() == null) {
            getJpaTemplate().persist(orderItem);
        } else {
            getJpaTemplate().merge(orderItem);
        }

        return orderItem;
    }

    public void delete(OrderItem orderItem) {
        getJpaTemplate().remove(orderItem);
    }

    public OrderItem load(Long id) {
        return getJpaTemplate().find(OrderItem.class, id);
    }

    /*
         * loads all records for this entity
         */
    public List<OrderItem> loadAll() {
        return getJpaTemplate().find("select orderItem from OrderItem orderItem");
    }

    //// FINDERS ///// 
    public List<OrderItem> searchByExample(OrderItem orderItem) {
        Session session = hibernateTemplate.getSessionFactory().openSession();

        try {
            Transaction tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(OrderItem.class)
                                       .add(Example.create(orderItem)
                                                   .enableLike(MatchMode.START)
                                                   .ignoreCase().excludeZeroes()
                                                   .excludeProperty("dateModified")
                                                   .excludeProperty("id")
                                                   .excludeProperty("dateCreated"));

            List<OrderItem> entities = criteria.list();

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
