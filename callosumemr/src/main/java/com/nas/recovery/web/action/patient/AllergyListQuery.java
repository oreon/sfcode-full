
package com.nas.recovery.web.action.patient;



import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.oreon.callosum.patient.Allergy;
import com.oreon.callosum.patient.Patient;
	
	
@Name("allergyList")
//@Scope(ScopeType.CONVERSATION)
public class AllergyListQuery extends AllergyListQueryBase implements java.io.Serializable{
	
	public List<Allergy> getAllergiesByPatient(Patient patient){
		//setMaxResults(10000);
		allergy.setPatient(patient);
		return getResultList();
	}
	
}
