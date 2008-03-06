
/**
 * This is generated code - to edit code or override methods use - ExamInstance class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.model.support.Range;
import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.service.BaseServiceImpl;

import com.oreon.kgauge.dao.ExamInstanceDao;
import com.oreon.kgauge.domain.ExamInstance;
import com.oreon.kgauge.service.ExamInstanceService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ExamInstanceServiceImplBase extends BaseServiceImpl<ExamInstance>
		implements
			ExamInstanceService {

	private static final Logger log = Logger
			.getLogger(ExamInstanceServiceImplBase.class);

	private ExamInstanceDao examInstanceDao;

	public void setExamInstanceDao(ExamInstanceDao examInstanceDao) {
		this.examInstanceDao = examInstanceDao;
	}

	@Override
	public GenericDAO<ExamInstance> getDao() {
		return examInstanceDao;
	}

	//// Delegate all crud operations to the Dao ////

	public ExamInstance save(ExamInstance examInstance) {
		Long id = examInstance.getId();

		examInstanceDao.save(examInstance);

		return examInstance;
	}

	public void delete(ExamInstance examInstance) {
		examInstanceDao.delete(examInstance);
	}

	public ExamInstance load(Long id) {
		return examInstanceDao.load(id);
	}

	public List<ExamInstance> loadAll() {
		return examInstanceDao.loadAll();
	}

	public List<ExamInstance> searchByExample(ExamInstance examInstance) {
		return examInstanceDao.searchByExample(examInstance);
	}

	public List<ExamInstance> searchByExample(ExamInstance examInstance,
			List<Range> rangeObjects) {
		return examInstanceDao.searchByExample(examInstance, rangeObjects);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
