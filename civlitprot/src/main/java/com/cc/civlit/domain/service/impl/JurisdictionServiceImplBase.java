
/**
 * This is generated code - to edit code or override methods use - Jurisdiction class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain.service.impl;

import com.cc.civlit.domain.courtdivisions.Jurisdiction;
import com.cc.civlit.domain.service.JurisdictionService;
import com.cc.civlit.domain.dao.JurisdictionDao;
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
public class JurisdictionServiceImplBase extends BaseServiceImpl<Jurisdiction>
		implements
			JurisdictionService {

	private static final Logger log = Logger
			.getLogger(JurisdictionServiceImplBase.class);

	private JurisdictionDao jurisdictionDao;

	public void setJurisdictionDao(JurisdictionDao jurisdictionDao) {
		this.jurisdictionDao = jurisdictionDao;
	}

	@Override
	public GenericDAO<Jurisdiction> getDao() {
		return jurisdictionDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Jurisdiction save(Jurisdiction jurisdiction) {
		Long id = jurisdiction.getId();

		jurisdictionDao.save(jurisdiction);

		return jurisdiction;
	}

	public void delete(Jurisdiction jurisdiction) {
		jurisdictionDao.delete(jurisdiction);
	}

	public Jurisdiction load(Long id) {
		return jurisdictionDao.load(id);
	}

	public List<Jurisdiction> loadAll() {
		return jurisdictionDao.loadAll();
	}

	public List<Jurisdiction> searchByExample(Jurisdiction jurisdiction) {
		return jurisdictionDao.searchByExample(jurisdiction);
	}

	public List<Jurisdiction> searchByExample(Jurisdiction jurisdiction,
			List<Range> rangeObjects) {
		return jurisdictionDao.searchByExample(jurisdiction, rangeObjects);
	}

	/**
	 * For tree view , this method returns top level
	 * elements (whose parent is null )
	 */
	public List<Jurisdiction> findTopLevelElements() {
		return jurisdictionDao.findTopLevelElements();
	}

}
