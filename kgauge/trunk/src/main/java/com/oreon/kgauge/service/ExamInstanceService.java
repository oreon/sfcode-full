package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.ExamInstance;
import com.oreon.kgauge.dao.ExamInstanceDao;
import org.witchcraft.model.support.service.BaseService;

import javax.jws.WebParam;
import javax.jws.WebService;

/** The Service interface for entity - ExamInstance
 * @author - Witchcraft Generated {Do not Modify } 
 * 
 */
@WebService
public interface ExamInstanceService
		extends
			ExamInstanceDao,
			BaseService<ExamInstance> {

}
