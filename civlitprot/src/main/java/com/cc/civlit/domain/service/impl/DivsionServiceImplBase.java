
/**
 * This is generated code - to edit code or override methods use - Divsion class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.cc.civlit.domain.service.impl;

import com.cc.civlit.domain.courtdivisions.Divsion;
import com.cc.civlit.domain.service.DivsionService;
import com.cc.civlit.domain.dao.DivsionDao;
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
public class DivsionServiceImplBase extends BaseServiceImpl<Divsion>
		implements
			DivsionService {

	private static final Logger log = Logger
			.getLogger(DivsionServiceImplBase.class);

	private DivsionDao divsionDao;

	public void setDivsionDao(DivsionDao divsionDao) {
		this.divsionDao = divsionDao;
	}

	@Override
	public GenericDAO<Divsion> getDao() {
		return divsionDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Divsion save(Divsion divsion) {
		Long id = divsion.getId();

		divsionDao.save(divsion);

		return divsion;
	}

	public void delete(Divsion divsion) {
		divsionDao.delete(divsion);
	}

	public Divsion load(Long id) {
		return divsionDao.load(id);
	}

	public List<Divsion> loadAll() {
		return divsionDao.loadAll();
	}

	public List<Divsion> searchByExample(Divsion divsion) {
		return divsionDao.searchByExample(divsion);
	}

	public List<Divsion> searchByExample(Divsion divsion,
			List<Range> rangeObjects) {
		return divsionDao.searchByExample(divsion, rangeObjects);
	}

}
