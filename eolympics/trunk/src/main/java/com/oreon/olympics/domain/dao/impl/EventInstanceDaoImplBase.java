package com.oreon.olympics.domain.dao.impl;

import com.oreon.olympics.domain.EventInstance;
import com.oreon.olympics.domain.dao.EventInstanceDao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.witchcraft.model.support.dao.BaseDao;

@Repository
public class EventInstanceDaoImplBase extends BaseDao<EventInstance>
		implements
			EventInstanceDao {

	//// FINDERS ///// 

}
