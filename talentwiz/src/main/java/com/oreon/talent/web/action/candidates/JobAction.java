package com.oreon.talent.web.action.candidates;

import org.hibernate.exception.ConstraintViolationException;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.witchcraft.exceptions.ContractViolationException;

import com.oreon.talent.candidates.JobApplication;

//@Scope(ScopeType.CONVERSATION)
@Name("jobAction")
public class JobAction extends JobActionBase implements java.io.Serializable {

	@In(create = true)
	CandidateAction candidateAction;

	@In(create = true)
	JobApplicationAction jobApplicationAction;

	@Override
	public void apply() {
		try {
			JobApplication jobApplication = new JobApplication();
			jobApplication.setJob(instance);
			jobApplication.setCandidate(candidateAction
					.getCurrentLoggedInCandidate());
			jobApplicationAction.persist(jobApplication);
			addInfoMessage("Successfully Applied for the job "
					+ instance.getDisplayName());
		}catch(ConstraintViolationException cve){
			addErrorMessage("You have already applied for this job ");
		}
		catch (Exception e) {
			addErrorMessage("Error Applying for the job - "
					+ e.getMessage());
		}
	}

}
