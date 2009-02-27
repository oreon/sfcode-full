
/**
 * This is generated code - to edit code or override methods use - Drug class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.service.impl;

import com.oreon.cerebrum.drugs.Drug;
import com.oreon.cerebrum.service.DrugService;
import com.oreon.cerebrum.dao.DrugDao;
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
public class DrugServiceImplBase extends BaseServiceImpl<Drug>
		implements
			DrugService {

	private static final Logger log = Logger
			.getLogger(DrugServiceImplBase.class);

	private DrugDao drugDao;

	public void setDrugDao(DrugDao drugDao) {
		this.drugDao = drugDao;
	}

	@Override
	public GenericDAO<Drug> getDao() {
		return drugDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Drug save(Drug drug) {
		Long id = drug.getId();
		checkUniqueConstraints(drug);
		drugDao.save(drug);

		return drug;
	}

	/** Before saving a record we need to ensure that no unique constraints
	 * will be violated. 
	 * @param customer
	 */
	private void checkUniqueConstraints(Drug drug) {
		Drug existingDrug = null;

		existingDrug = drugDao.findByName(drug.getName());
		ensureUnique(drug, existingDrug, "Entity.exists.withName");

	}

	public void delete(Drug drug) {
		drugDao.delete(drug);
	}

	public Drug load(Long id) {
		return drugDao.load(id);
	}

	public List<Drug> loadAll() {
		return drugDao.loadAll();
	}

	public Drug findByName(String name) {
		return drugDao.findByName(name);
	}

	public List<Drug> searchByExample(Drug drug) {
		return drugDao.searchByExample(drug);
	}

	public List<Drug> searchByExample(Drug drug, List<Range> rangeObjects) {
		return drugDao.searchByExample(drug, rangeObjects);
	}

	/** This method should be overridden by classes that want to filter the load all behavior e.g.
	 * showing 
	 * @return
	 */
	public Drug getFilterRecord() {
		return null;
	}

}
