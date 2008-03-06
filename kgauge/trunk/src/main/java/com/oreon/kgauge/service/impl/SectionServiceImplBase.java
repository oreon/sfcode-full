
/**
 * This is generated code - to edit code or override methods use - Section class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.model.support.Range;
import org.witchcraft.model.support.dao.GenericDAO;
import org.witchcraft.model.support.service.BaseServiceImpl;

import com.oreon.kgauge.dao.SectionDao;
import com.oreon.kgauge.domain.Section;
import com.oreon.kgauge.service.SectionService;

@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
public class SectionServiceImplBase extends BaseServiceImpl<Section>
		implements
			SectionService {

	private static final Logger log = Logger
			.getLogger(SectionServiceImplBase.class);

	private SectionDao sectionDao;

	public void setSectionDao(SectionDao sectionDao) {
		this.sectionDao = sectionDao;
	}

	@Override
	public GenericDAO<Section> getDao() {
		return sectionDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Section save(Section section) {
		Long id = section.getId();

		sectionDao.save(section);

		return section;
	}

	public void delete(Section section) {
		sectionDao.delete(section);
	}

	public Section load(Long id) {
		return sectionDao.load(id);
	}

	public List<Section> loadAll() {
		return sectionDao.loadAll();
	}

	public List<Section> searchByExample(Section section) {
		return sectionDao.searchByExample(section);
	}

	public List<Section> searchByExample(Section section,
			List<Range> rangeObjects) {
		return sectionDao.searchByExample(section, rangeObjects);
	}

	/*
	public List query(String queryString, Object... params) {
		return basicDAO.query(queryString, params);
	}*/

}
