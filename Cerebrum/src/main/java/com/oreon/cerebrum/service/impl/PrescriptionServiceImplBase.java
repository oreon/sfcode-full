
/**
 * This is generated code - to edit code or override methods use - Prescription class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.service.impl;

import com.oreon.cerebrum.prescriptions.Prescription;
import com.oreon.cerebrum.service.PrescriptionService;
import com.oreon.cerebrum.dao.PrescriptionDao;
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
public class PrescriptionServiceImplBase extends BaseServiceImpl<Prescription>
		implements
			PrescriptionService {

	private static final Logger log = Logger
			.getLogger(PrescriptionServiceImplBase.class);

	private PrescriptionDao prescriptionDao;

	public void setPrescriptionDao(PrescriptionDao prescriptionDao) {
		this.prescriptionDao = prescriptionDao;
	}

	@Override
	public GenericDAO<Prescription> getDao() {
		return prescriptionDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Prescription save(Prescription prescription) {
		Long id = prescription.getId();

		prescriptionDao.save(prescription);

		return prescription;
	}

	public void delete(Prescription prescription) {
		prescriptionDao.delete(prescription);
	}

	public Prescription load(Long id) {
		return prescriptionDao.load(id);
	}

	public List<Prescription> loadAll() {
		return prescriptionDao.loadAll();
	}

	public List<Prescription> searchByExample(Prescription prescription) {
		return prescriptionDao.searchByExample(prescription);
	}

	public List<Prescription> searchByExample(Prescription prescription,
			List<Range> rangeObjects) {
		return prescriptionDao.searchByExample(prescription, rangeObjects);
	}

	/** This method should be overridden by classes that want to filter the load all behavior e.g.
	 * showing 
	 * @return
	 */
	public Prescription getFilterRecord() {
		return null;
	}

}
