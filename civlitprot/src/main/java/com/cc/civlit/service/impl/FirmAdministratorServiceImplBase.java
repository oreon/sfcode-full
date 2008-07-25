
/**
 * This is generated code - to edit code or override methods use - FirmAdministrator class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.service.impl;

import com.cc.civlit.domain.FirmAdministrator;
import com.cc.civlit.service.FirmAdministratorService;
import com.cc.civlit.dao.FirmAdministratorDao;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import org.apache.log4j.Logger;

import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.service.BaseServiceImpl;

import javax.jws.WebService;

import org.witchcraft.model.support.Range;

import com.cc.civlit.domain.auth.GrantedRole;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class FirmAdministratorServiceImplBase
		extends
			BaseServiceImpl<FirmAdministrator>
		implements
			FirmAdministratorService {

	private static final Logger log = Logger
			.getLogger(FirmAdministratorServiceImplBase.class);

	private FirmAdministratorDao firmAdministratorDao;

	public void setFirmAdministratorDao(
			FirmAdministratorDao firmAdministratorDao) {
		this.firmAdministratorDao = firmAdministratorDao;
	}

	@Override
	public GenericDAO<FirmAdministrator> getDao() {
		return firmAdministratorDao;
	}

	//// Delegate all crud operations to the Dao ////

	public FirmAdministrator save(FirmAdministrator firmAdministrator) {
		Long id = firmAdministrator.getId();
		checkUniqueConstraints(firmAdministrator);
		firmAdministratorDao.save(firmAdministrator);

		if (id == null) //creating user for first time, assign authority
			assignDefaultAuthority(firmAdministrator);

		return firmAdministrator;
	}

	/** Before saving a record we need to ensure that no unique constraints
	 * will be violated. 
	 * @param customer
	 */
	private void checkUniqueConstraints(FirmAdministrator firmAdministrator) {
		FirmAdministrator existingFirmAdministrator = null;

		existingFirmAdministrator = firmAdministratorDao
				.findByEmail(firmAdministrator.getEmail());
		ensureUnique(firmAdministrator, existingFirmAdministrator,
				"Entity.exists.withEmail");

		existingFirmAdministrator = firmAdministratorDao
				.findByUsername(firmAdministrator.getUser().getUsername());
		ensureUnique(firmAdministrator, existingFirmAdministrator,
				"Entity.exists.withUsername");

	}

	private void assignDefaultAuthority(FirmAdministrator firmAdministrator) {
		GrantedRole authority = new GrantedRole();
		authority.setName("ROLE_FIRMADMINISTRATOR");

		firmAdministrator.getUser().addGrantedRole(authority);
	}

	public void delete(FirmAdministrator firmAdministrator) {
		firmAdministratorDao.delete(firmAdministrator);
	}

	public FirmAdministrator load(Long id) {
		return firmAdministratorDao.load(id);
	}

	public List<FirmAdministrator> loadAll() {
		return firmAdministratorDao.loadAll();
	}

	public FirmAdministrator findByEmail(String email) {
		return firmAdministratorDao.findByEmail(email);
	}

	public FirmAdministrator findByUsername(String username) {
		return firmAdministratorDao.findByUsername(username);
	}

	public List<FirmAdministrator> searchByExample(
			FirmAdministrator firmAdministrator) {
		return firmAdministratorDao.searchByExample(firmAdministrator);
	}

	public List<FirmAdministrator> searchByExample(
			FirmAdministrator firmAdministrator, List<Range> rangeObjects) {
		return firmAdministratorDao.searchByExample(firmAdministrator,
				rangeObjects);
	}

}
