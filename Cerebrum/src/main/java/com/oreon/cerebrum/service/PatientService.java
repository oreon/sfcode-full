package com.oreon.cerebrum.service;

import com.oreon.cerebrum.prescriptions.Patient;
import com.oreon.cerebrum.dao.PatientDao;
import org.witchcraft.model.support.service.BaseService;

import javax.jws.WebParam;
import javax.jws.WebService;

/** The Service interface for entity - Patient
 * @author - Witchcraft Generated {Do not Modify } 
 * 
 */
@WebService
public interface PatientService extends PatientDao, BaseService<Patient> {

}
