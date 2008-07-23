
/**
 * This is generated code - to edit code or override methods use - FilingOffice class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain.service.impl;

import com.cc.civlit.domain.courtdivisions.FilingOffice;
import com.cc.civlit.domain.service.FilingOfficeService;
import com.cc.civlit.domain.dao.FilingOfficeDao;
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
public class FilingOfficeServiceImplBase extends BaseServiceImpl<FilingOffice>
		implements
			FilingOfficeService {

	private static final Logger log = Logger
			.getLogger(FilingOfficeServiceImplBase.class);

	private FilingOfficeDao filingOfficeDao;

	public void setFilingOfficeDao(FilingOfficeDao filingOfficeDao) {
		this.filingOfficeDao = filingOfficeDao;
	}

	@Override
	public GenericDAO<FilingOffice> getDao() {
		return filingOfficeDao;
	}

	//// Delegate all crud operations to the Dao ////

	public FilingOffice save(FilingOffice filingOffice) {
		Long id = filingOffice.getId();

		filingOfficeDao.save(filingOffice);

		return filingOffice;
	}

	public void delete(FilingOffice filingOffice) {
		filingOfficeDao.delete(filingOffice);
	}

	public FilingOffice load(Long id) {
		return filingOfficeDao.load(id);
	}

	public List<FilingOffice> loadAll() {
		return filingOfficeDao.loadAll();
	}

	public List<FilingOffice> searchByExample(FilingOffice filingOffice) {
		return filingOfficeDao.searchByExample(filingOffice);
	}

	public List<FilingOffice> searchByExample(FilingOffice filingOffice,
			List<Range> rangeObjects) {
		return filingOfficeDao.searchByExample(filingOffice, rangeObjects);
	}

}
