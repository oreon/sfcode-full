package com.oreon.kgauge.dao;

import com.oreon.kgauge.domain.Candidate;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface CandidateDao extends GenericDAO<Candidate> {

	public Candidate findByUserName(String userName);

	public Candidate findByEmail(String email);

}
