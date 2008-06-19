package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.Question;
import com.oreon.kgauge.dao.QuestionDao;
import org.witchcraft.model.support.service.BaseService;

import javax.jws.WebParam;
import javax.jws.WebService;

/** The Service interface for entity - Question
 * @author - Witchcraft Generated {Do not Modify } 
 * 
 */
@WebService
public interface QuestionService extends QuestionDao, BaseService<Question> {

}
