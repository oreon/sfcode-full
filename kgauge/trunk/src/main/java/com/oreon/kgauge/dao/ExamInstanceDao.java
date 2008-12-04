package com.oreon.kgauge.dao;

import com.oreon.kgauge.domain.ExamInstance;
import org.witchcraft.model.support.dao.GenericDAO;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ExamInstanceDao extends GenericDAO<ExamInstance> {

	public Integer findCandidateScore(ExamInstance examInstance);

	public Integer findMaxScore(ExamInstance examInstance);

}
