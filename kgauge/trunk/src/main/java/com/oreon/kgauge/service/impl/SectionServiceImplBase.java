
/**
 * This is generated code - to edit code or override methods use - Section class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.kgauge.service.impl;

import com.oreon.kgauge.domain.Section;
import com.oreon.kgauge.service.SectionService;
import com.oreon.kgauge.dao.SectionDao;
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

	/** This method should be overridden by classes that want to filter the load all behavior e.g.
	 * showing 
	 * @return
	 */
	public Section getFilterRecord() {
		return null;
	}

}
