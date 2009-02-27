
/**
 * This is generated code - to edit code or override methods use - Patient class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package com.oreon.cerebrum.service.impl;

import com.oreon.cerebrum.prescriptions.Patient;
import com.oreon.cerebrum.service.PatientService;
import com.oreon.cerebrum.dao.PatientDao;
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
public class PatientServiceImplBase extends BaseServiceImpl<Patient>
		implements
			PatientService {

	private static final Logger log = Logger
			.getLogger(PatientServiceImplBase.class);

	private PatientDao patientDao;

	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}

	@Override
	public GenericDAO<Patient> getDao() {
		return patientDao;
	}

	//// Delegate all crud operations to the Dao ////

	public Patient save(Patient patient) {
		Long id = patient.getId();

		patientDao.save(patient);

		return patient;
	}

	public void delete(Patient patient) {
		patientDao.delete(patient);
	}

	public Patient load(Long id) {
		return patientDao.load(id);
	}

	public List<Patient> loadAll() {
		return patientDao.loadAll();
	}

	public List<Patient> searchByExample(Patient patient) {
		return patientDao.searchByExample(patient);
	}

	public List<Patient> searchByExample(Patient patient,
			List<Range> rangeObjects) {
		return patientDao.searchByExample(patient, rangeObjects);
	}

	/** This method should be overridden by classes that want to filter the load all behavior e.g.
	 * showing 
	 * @return
	 */
	public Patient getFilterRecord() {
		return null;
	}

}
