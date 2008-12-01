package com.oreon.kgauge.service;

import com.oreon.kgauge.domain.Candidate;
import com.oreon.kgauge.dao.CandidateDao;
import org.witchcraft.model.support.service.BaseService;

import javax.jws.WebParam;
import javax.jws.WebService;

/** The Service interface for entity - Candidate
 * @author - Witchcraft Generated {Do not Modify } 
 * 
 */
@WebService
public interface CandidateService extends CandidateDao, BaseService<Candidate> {

	public Candidate getLoggedInCandidate();

}
