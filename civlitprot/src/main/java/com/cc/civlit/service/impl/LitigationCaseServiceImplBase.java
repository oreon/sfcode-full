
/**
 * This is generated code - to edit code or override methods use - LitigationCase class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.service.impl;

import com.cc.civlit.domain.LitigationCase;
import com.cc.civlit.service.LitigationCaseService;
import com.cc.civlit.dao.LitigationCaseDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

import javax.jws.WebService;

import org.witchcraft.model.support.Range;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class LitigationCaseServiceImplBase
		extends
			BaseServiceImpl<LitigationCase> implements LitigationCaseService {

	private static final Logger log = Logger
			.getLogger(LitigationCaseServiceImplBase.class);

	private LitigationCaseDao litigationCaseDao;

	public void setLitigationCaseDao(LitigationCaseDao litigationCaseDao) {
		this.litigationCaseDao = litigationCaseDao;
	}

	@Override
	public GenericDAO<LitigationCase> getDao() {
		return litigationCaseDao;
	}

	//// Delegate all crud operations to the Dao ////

	public LitigationCase save(LitigationCase litigationCase) {
		Long id = litigationCase.getId();

		litigationCaseDao.save(litigationCase);

		return litigationCase;
	}

	public void delete(LitigationCase litigationCase) {
		litigationCaseDao.delete(litigationCase);
	}

	public LitigationCase load(Long id) {
		return litigationCaseDao.load(id);
	}

	public List<LitigationCase> loadAll() {
		return litigationCaseDao.loadAll();
	}

	public List<LitigationCase> searchByExample(LitigationCase litigationCase) {
		return litigationCaseDao.searchByExample(litigationCase);
	}

	public List<LitigationCase> searchByExample(LitigationCase litigationCase,
			List<Range> rangeObjects) {
		return litigationCaseDao.searchByExample(litigationCase, rangeObjects);
	}

}
