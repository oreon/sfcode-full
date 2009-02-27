package com.oreon.cerebrum.service;

import com.oreon.cerebrum.prescriptions.Prescription;
import com.oreon.cerebrum.dao.PrescriptionDao;
import org.witchcraft.model.support.service.BaseService;

import javax.jws.WebParam;
import javax.jws.WebService;

/** The Service interface for entity - Prescription
 * @author - Witchcraft Generated {Do not Modify } 
 * 
 */
@WebService
public interface PrescriptionService
		extends
			PrescriptionDao,
			BaseService<Prescription> {

}
