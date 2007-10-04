package bizobjects.dao.impl;

import bizobjects.Product;
import bizobjects.dao.ProductDao;

import bizobjects.Customer;
import bizobjects.dao.CustomerDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class ProductDaoImplBase extends BaseDao<Product> implements ProductDao {

	//// FINDERS ///// 

}
