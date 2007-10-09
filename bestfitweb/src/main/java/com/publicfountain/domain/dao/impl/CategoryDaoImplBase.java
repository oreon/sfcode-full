package com.publicfountain.domain.dao.impl;

import com.publicfountain.domain.Category;
import com.publicfountain.domain.dao.CategoryDao;

import bizobjects.Customer;
import bizobjects.dao.CustomerDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class CategoryDaoImplBase extends BaseDao<Category>
		implements
			CategoryDao {

	//// FINDERS ///// 

}
