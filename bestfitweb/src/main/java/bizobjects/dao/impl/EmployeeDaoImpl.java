package bizobjects.dao.impl;

import bizobjects.Employee;

import bizobjects.dao.EmployeeDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.jpa.support.JpaDaoSupport;

import java.util.List;


public class EmployeeDaoImpl extends JpaDaoSupport implements EmployeeDao {
    private HibernateTemplate hibernateTemplate;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    /**
    * This method saves or updates the given entity based upon whether the id is null
    */
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            getJpaTemplate().persist(employee);
        } else {
            getJpaTemplate().merge(employee);
        }

        return employee;
    }

    public void delete(Employee employee) {
        getJpaTemplate().remove(employee);
    }

    public Employee load(Long id) {
        return getJpaTemplate().find(Employee.class, id);
    }

    /*
         * loads all records for this entity
         */
    public List<Employee> loadAll() {
        return getJpaTemplate().find("select employee from Employee employee");
    }

    //// FINDERS ///// 
    public List<Employee> findBycode(Object code) {
        return getJpaTemplate()
                   .find("select c from Employee c where c.code = ?1", code);
    }

    public List<Employee> findByfirstName(Object firstName) {
        return getJpaTemplate()
                   .find("select c from Employee c where c.firstName = ?1",
            firstName);
    }

    public List<Employee> findBylastName(Object lastName) {
        return getJpaTemplate()
                   .find("select c from Employee c where c.lastName = ?1",
            lastName);
    }

    public List<Employee> searchByExample(Employee employee) {
        Session session = hibernateTemplate.getSessionFactory().openSession();

        try {
            Transaction tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Employee.class)
                                       .add(Example.create(employee)
                                                   .enableLike(MatchMode.START)
                                                   .ignoreCase().excludeZeroes()
                                                   .excludeProperty("dateModified")
                                                   .excludeProperty("id")
                                                   .excludeProperty("dateCreated"));

            List<Employee> entities = criteria.list();

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
