package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.ExamCreator;
import com.oreon.kgauge.dao.ExamCreatorDao;
import org.witchcraft.model.support.service.BaseService;

import javax.jws.WebParam;
import javax.jws.WebService;

/** The Service interface for entity - ExamCreator
 * @author - Witchcraft Generated {Do not Modify } 
 * 
 */
@WebService
public interface ExamCreatorService
		extends
			ExamCreatorDao,
			BaseService<ExamCreator> {

	public ExamCreator getLoggedInExamCreator();

}
