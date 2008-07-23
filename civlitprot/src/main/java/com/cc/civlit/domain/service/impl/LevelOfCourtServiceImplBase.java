
/**
 * This is generated code - to edit code or override methods use - LevelOfCourt class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain.service.impl;

import com.cc.civlit.domain.courtdivisions.LevelOfCourt;
import com.cc.civlit.domain.service.LevelOfCourtService;
import com.cc.civlit.domain.dao.LevelOfCourtDao;
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
public class LevelOfCourtServiceImplBase extends BaseServiceImpl<LevelOfCourt>
		implements
			LevelOfCourtService {

	private static final Logger log = Logger
			.getLogger(LevelOfCourtServiceImplBase.class);

	private LevelOfCourtDao levelOfCourtDao;

	public void setLevelOfCourtDao(LevelOfCourtDao levelOfCourtDao) {
		this.levelOfCourtDao = levelOfCourtDao;
	}

	@Override
	public GenericDAO<LevelOfCourt> getDao() {
		return levelOfCourtDao;
	}

	//// Delegate all crud operations to the Dao ////

	public LevelOfCourt save(LevelOfCourt levelOfCourt) {
		Long id = levelOfCourt.getId();

		levelOfCourtDao.save(levelOfCourt);

		return levelOfCourt;
	}

	public void delete(LevelOfCourt levelOfCourt) {
		levelOfCourtDao.delete(levelOfCourt);
	}

	public LevelOfCourt load(Long id) {
		return levelOfCourtDao.load(id);
	}

	public List<LevelOfCourt> loadAll() {
		return levelOfCourtDao.loadAll();
	}

	public List<LevelOfCourt> searchByExample(LevelOfCourt levelOfCourt) {
		return levelOfCourtDao.searchByExample(levelOfCourt);
	}

	public List<LevelOfCourt> searchByExample(LevelOfCourt levelOfCourt,
			List<Range> rangeObjects) {
		return levelOfCourtDao.searchByExample(levelOfCourt, rangeObjects);
	}

}
