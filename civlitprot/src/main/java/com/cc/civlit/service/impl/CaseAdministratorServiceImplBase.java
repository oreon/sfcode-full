
/**
 * This is generated code - to edit code or override methods use - CaseAdministrator class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.service.impl;

import com.cc.civlit.domain.CaseAdministrator;
import com.cc.civlit.service.CaseAdministratorService;
import com.cc.civlit.dao.CaseAdministratorDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

import javax.jws.WebService;

import org.witchcraft.model.support.Range;

import com.oreon.kgauge.domain.GrantedRole;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class CaseAdministratorServiceImplBase
		extends
			BaseServiceImpl<CaseAdministrator>
		implements
			CaseAdministratorService {

	private static final Logger log = Logger
			.getLogger(CaseAdministratorServiceImplBase.class);

	private CaseAdministratorDao caseAdministratorDao;

	public void setCaseAdministratorDao(
			CaseAdministratorDao caseAdministratorDao) {
		this.caseAdministratorDao = caseAdministratorDao;
	}

	@Override
	public GenericDAO<CaseAdministrator> getDao() {
		return caseAdministratorDao;
	}

	//// Delegate all crud operations to the Dao ////

	public CaseAdministrator save(CaseAdministrator caseAdministrator) {
		Long id = caseAdministrator.getId();
		checkUniqueConstraints(caseAdministrator);
		caseAdministratorDao.save(caseAdministrator);

		return caseAdministrator;
	}

	/** Before saving a record we need to ensure that no unique constraints
	 * will be violated. 
	 * @param customer
	 */
	private void checkUniqueConstraints(CaseAdministrator caseAdministrator) {
		CaseAdministrator existingCaseAdministrator = null;

		existingCaseAdministrator = caseAdministratorDao
				.findByEmail(caseAdministrator.getEmail());
		ensureUnique(caseAdministrator, existingCaseAdministrator,
				"Entity.exists.withEmail");

	}

	public void delete(CaseAdministrator caseAdministrator) {
		caseAdministratorDao.delete(caseAdministrator);
	}

	public CaseAdministrator load(Long id) {
		return caseAdministratorDao.load(id);
	}

	public List<CaseAdministrator> loadAll() {
		return caseAdministratorDao.loadAll();
	}

	public CaseAdministrator findByEmail(String email) {
		return caseAdministratorDao.findByEmail(email);
	}

	public List<CaseAdministrator> searchByExample(
			CaseAdministrator caseAdministrator) {
		return caseAdministratorDao.searchByExample(caseAdministrator);
	}

	public List<CaseAdministrator> searchByExample(
			CaseAdministrator caseAdministrator, List<Range> rangeObjects) {
		return caseAdministratorDao.searchByExample(caseAdministrator,
				rangeObjects);
	}

}
