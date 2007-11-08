package com.oreon.olympics.domain.dao.impl;

import com.oreon.olympics.domain.Category;
import com.oreon.olympics.domain.dao.CategoryDao;

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
