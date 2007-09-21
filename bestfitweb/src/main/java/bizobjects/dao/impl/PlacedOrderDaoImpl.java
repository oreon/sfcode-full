package bizobjects.dao.impl;

import bizobjects.PlacedOrder;

import bizobjects.dao.PlacedOrderDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import java.util.List;


public class PlacedOrderDaoImpl extends JpaDaoSupport implements PlacedOrderDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    /**
    * This method saves or updates the given entity based upon whether the id is null
    */
    public PlacedOrder save(PlacedOrder placedOrder) {
        if (placedOrder.getId() == null) {
            getJpaTemplate().persist(placedOrder);
        } else {
            getJpaTemplate().merge(placedOrder);
        }

        return placedOrder;
    }

    public void delete(PlacedOrder placedOrder) {
        getJpaTemplate().remove(placedOrder);
    }

    public PlacedOrder load(Long id) {
        return getJpaTemplate().find(PlacedOrder.class, id);
    }

    /*
         * loads all records for this entity
         */
    public List<PlacedOrder> loadAll() {
        return getJpaTemplate()
                   .find("select placedOrder from PlacedOrder placedOrder");
    }

    //// FINDERS ///// 
    public List<PlacedOrder> searchByExample(PlacedOrder placedOrder) {
        Session session = hibernateTemplate.getSessionFactory().openSession();

        try {
            Transaction tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(PlacedOrder.class)
                                       .add(Example.create(placedOrder)
                                                   .enableLike(MatchMode.START)
                                                   .ignoreCase().excludeZeroes()
                                                   .excludeProperty("dateModified")
                                                   .excludeProperty("id")
                                                   .excludeProperty("dateCreated"));

            List<PlacedOrder> entities = criteria.list();

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
