
/**
 * This is generated code - to edit code or override methods use - DrugCode class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.service.impl;

import com.oreon.cerebrum.drugs.DrugCode;
import com.oreon.cerebrum.service.DrugCodeService;
import com.oreon.cerebrum.dao.DrugCodeDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.userdetails.UserDetails;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

import javax.jws.WebService;

import org.witchcraft.model.support.Range;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class DrugCodeServiceImplBase extends BaseServiceImpl<DrugCode>
		implements
			DrugCodeService {

	private static final Logger log = Logger
			.getLogger(DrugCodeServiceImplBase.class);

	private DrugCodeDao drugCodeDao;

	public void setDrugCodeDao(DrugCodeDao drugCodeDao) {
		this.drugCodeDao = drugCodeDao;
	}

	@Override
	public GenericDAO<DrugCode> getDao() {
		return drugCodeDao;
	}

	//// Delegate all crud operations to the Dao ////

	public DrugCode save(DrugCode drugCode) {
		Long id = drugCode.getId();
		checkUniqueConstraints(drugCode);
		drugCodeDao.save(drugCode);

		return drugCode;
	}

	/** Before saving a record we need to ensure that no unique constraints
	 * will be violated. 
	 * @param customer
	 */
	private void checkUniqueConstraints(DrugCode drugCode) {
		DrugCode existingDrugCode = null;

		existingDrugCode = drugCodeDao.findByCode(drugCode.getCode());
		ensureUnique(drugCode, existingDrugCode, "Entity.exists.withCode");

	}

	public void delete(DrugCode drugCode) {
		drugCodeDao.delete(drugCode);
	}

	public DrugCode load(Long id) {
		return drugCodeDao.load(id);
	}

	public List<DrugCode> loadAll() {
		return drugCodeDao.loadAll();
	}

	public DrugCode findByCode(String code) {
		return drugCodeDao.findByCode(code);
	}

	public List<DrugCode> searchByExample(DrugCode drugCode) {
		return drugCodeDao.searchByExample(drugCode);
	}

	public List<DrugCode> searchByExample(DrugCode drugCode,
			List<Range> rangeObjects) {
		return drugCodeDao.searchByExample(drugCode, rangeObjects);
	}

	/** This method should be overridden by classes that want to filter the load all behavior e.g.
	 * showing 
	 * @return
	 */
	public DrugCode getFilterRecord() {
		return null;
	}

}
