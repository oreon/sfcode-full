package com.oreon.jshoppingcart.dao.impl;

import com.oreon.jshoppingcart.domain.Customer;
import com.oreon.jshoppingcart.dao.CustomerDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class CustomerDaoImplBase extends BaseDao<Customer>
		implements
			CustomerDao {

	//// FINDERS ///// 

}
