
/**
 * This is generated code - to edit code or override methods use - Firm class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.service.impl;

import com.cc.civlit.domain.Firm;
import com.cc.civlit.service.FirmService;
import com.cc.civlit.dao.FirmDao;
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
public class FirmServiceImplBase extends BaseServiceImpl<Firm>
		implements
			FirmService {

	private static final Logger log = Logger
			.getLogger(FirmServiceImplBase.class);

	private FirmDao firmDao;

	public void setFirmDao(FirmDao firmDao) {
		this.firmDao = firmDao;
	}

	@Override
	public GenericDAO<Firm> getDao() {
		return firmDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Firm save(Firm firm) {
		Long id = firm.getId();
		checkUniqueConstraints(firm);
		firmDao.save(firm);

		return firm;
	}

	/** Before saving a record we need to ensure that no unique constraints
	 * will be violated. 
	 * @param customer
	 */
	private void checkUniqueConstraints(Firm firm) {
		Firm existingFirm = null;

		existingFirm = firmDao.findByEmail(firm.getContactDetails().getEmail());
		ensureUnique(firm, existingFirm, "Entity.exists.withEmail");

	}

	public void delete(Firm firm) {
		firmDao.delete(firm);
	}

	public Firm load(Long id) {
		return firmDao.load(id);
	}

	public List<Firm> loadAll() {
		return firmDao.loadAll();
	}

	public Firm findByEmail(String email) {
		return firmDao.findByEmail(email);
	}

	public List<Firm> searchByExample(Firm firm) {
		return firmDao.searchByExample(firm);
	}

	public List<Firm> searchByExample(Firm firm, List<Range> rangeObjects) {
		return firmDao.searchByExample(firm, rangeObjects);
	}

}
