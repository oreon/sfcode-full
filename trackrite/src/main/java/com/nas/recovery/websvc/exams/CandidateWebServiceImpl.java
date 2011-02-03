package com.nas.recovery.websvc.exams;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.exams.Candidate;

@WebService(endpointInterface = "com.nas.recovery.websvc.exams.CandidateWebService", serviceName = "CandidateWebService")
@Name("candidateWebService")
public class CandidateWebServiceImpl implements CandidateWebService {

	@In(create = true)
	com.nas.recovery.web.action.exams.CandidateAction candidateAction;

	public Candidate loadById(Long id) {
		return candidateAction.loadFromId(id);
	}

	public List<Candidate> findByExample(Candidate exampleCandidate) {
		return candidateAction.search(exampleCandidate);
	}

	public void save(Candidate candidate) {
		candidateAction.persist(candidate);
	}

}
