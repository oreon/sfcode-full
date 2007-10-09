package com.publicfountain.domain.service.impl;

import com.publicfountain.domain.Topic;
import com.publicfountain.domain.service.TopicService;
import com.publicfountain.domain.dao.TopicDao;
import java.util.List;
import com.publicfountain.domain.service.TopicService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import usermanagement.Authority;
import usermanagement.service.AuthorityService;

import org.witchcraft.model.support.errorhandling.BusinessException;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class TopicServiceImplBase implements TopicService {

	private static final Logger log = Logger
			.getLogger(TopicServiceImplBase.class);

	private TopicDao topicDao;

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Topic save(Topic topic) {

		topicDao.save(topic);

		return topic;
	}

	public void delete(Topic topic) {
		topicDao.delete(topic);
	}

	public Topic load(Long id) {
		return topicDao.load(id);
	}

	public List<Topic> loadAll() {
		return topicDao.loadAll();
	}

	public List<Topic> searchByExample(Topic topic) {
		return topicDao.searchByExample(topic);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
