@FilterDef(name = "facilityFilterDef", defaultCondition = "facility.id = :facilityId", 
		parameters = @ParamDef(name = "facilityId", type = "long"))
package com.oreon.callosum.patient;

 
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
 