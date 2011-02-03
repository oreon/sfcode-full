package com.nas.recovery.websvc.exams;

import javax.jws.WebService;
import org.wc.trackrite.exams.Candidate;
import java.util.List;

@WebService
public interface CandidateWebService {

	public Candidate loadById(Long id);

	public List<Candidate> findByExample(Candidate exampleCandidate);

	public void save(Candidate candidate);

}
