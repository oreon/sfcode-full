package com.wc.shopper.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.wc.shopper.domain.Car;
import com.wc.shopper.domain.Customer;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class LazyCarDataModel extends LazyDataModel<Car> {
	
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
    
    private List<Car> datasource;
    
    public LazyCarDataModel(List<Car> datasource) {
        this.datasource = datasource;
    }
    
    @Override
    public Car getRowData(String rowKey) {
        for(Car car : datasource) {
            if(car.getModel().equals(rowKey))
                return car;
        }

        return null;
    }

    @Override
    public Object getRowKey(Car car) {
        return car.getModel();
    }

    @Override
 	// @Transactional(propagation = Propagation.REQUIRED)
 	public List<Car>  load(int index, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters)  {

 		Session s = (Session) entityManager.getDelegate();

 		Criteria crit = s.createCriteria(Car.class);

 		if (sortField != null && !sortField.isEmpty()) {
 			if (sortOrder == SortOrder.ASCENDING) {
 				crit = crit.addOrder(Order.asc(sortField));
 			} else {
 				crit = crit.addOrder(Order.desc(sortField));
 			}
 		}

 		if (!filters.isEmpty()) {
 			Iterator<Entry<String, String>> iterator = filters.entrySet()
 					.iterator();
 			while (iterator.hasNext()) {
 				Entry<String, String> entry = iterator.next();
 				crit = crit.add(Restrictions.like(entry.getKey(),
 						entry.getValue(), MatchMode.START));
 			}
 		}

 		crit = crit.setFirstResult(index).setMaxResults(pageSize);

 		return crit.list();
 		
 		//return lazyCustomers;
 	}
    
    //@Override
 	// @Transactional(propagation = Propagation.REQUIRED)
 	public Long getCarCount() {
 		Session s = (Session) entityManager.getDelegate();

 		Criteria crit = s.createCriteria(Customer.class).setProjection(
 				Projections.rowCount());

 		return (Long) crit.list().get(0);
 	}
}
                    