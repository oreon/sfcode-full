
/**
 * This is generated code - to edit code or override methods use - ExamCreator class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.service.impl;

import com.oreon.kgauge.domain.ExamCreator;
import com.oreon.kgauge.service.ExamCreatorService;
import com.oreon.kgauge.dao.ExamCreatorDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

import javax.jws.WebService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class ExamCreatorServiceImplBase extends BaseServiceImpl<ExamCreator>
		implements
			ExamCreatorService {

	private static final Logger log = Logger
			.getLogger(ExamCreatorServiceImplBase.class);

	private ExamCreatorDao examCreatorDao;

	public void setExamCreatorDao(ExamCreatorDao examCreatorDao) {
		this.examCreatorDao = examCreatorDao;
	}

	@Override
	public GenericDAO<ExamCreator> getDao() {
		return examCreatorDao;
	}

	//// Delegate all crud operations to the Dao ////

	public ExamCreator save(ExamCreator examCreator) {
		Long id = examCreator.getId();
		checkUniqueConstraints(examCreator);
		examCreatorDao.save(examCreator);

		return examCreator;
	}

	/** Before saving a record we need to ensure that no unique constraints
	 * will be violated. 
	 * @param customer
	 */
	private void checkUniqueConstraints(ExamCreator examCreator) {
		ExamCreator existingExamCreator = null;

		existingExamCreator = examCreatorDao.findByUserName(examCreator
				.getUser().getUserName());
		ensureUnique(examCreator, existingExamCreator,
				"Entity.exists.withUserName");

		existingExamCreator = examCreatorDao.findByEmail(examCreator
				.getContactDetails().getEmail());
		ensureUnique(examCreator, existingExamCreator,
				"Entity.exists.withEmail");

	}

	public void delete(ExamCreator examCreator) {
		examCreatorDao.delete(examCreator);
	}

	public ExamCreator load(Long id) {
		return examCreatorDao.load(id);
	}

	public List<ExamCreator> loadAll() {
		return examCreatorDao.loadAll();
	}

	public ExamCreator findByUserName(String userName) {
		return examCreatorDao.findByUserName(userName);
	}

	public ExamCreator findByEmail(String email) {
		return examCreatorDao.findByEmail(email);
	}

	public List<ExamCreator> searchByExample(ExamCreator examCreator) {
		return examCreatorDao.searchByExample(examCreator);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
