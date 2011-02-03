package com.nas.recovery.websvc.issues;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.issues.MilestoneRelease;

@WebService(endpointInterface = "com.nas.recovery.websvc.issues.MilestoneReleaseWebService", serviceName = "MilestoneReleaseWebService")
@Name("milestoneReleaseWebService")
public class MilestoneReleaseWebServiceImpl
		implements
			MilestoneReleaseWebService {

	@In(create = true)
	com.nas.recovery.web.action.issues.MilestoneReleaseAction milestoneReleaseAction;

	public MilestoneRelease loadById(Long id) {
		return milestoneReleaseAction.loadFromId(id);
	}

	public List<MilestoneRelease> findByExample(
			MilestoneRelease exampleMilestoneRelease) {
		return milestoneReleaseAction.search(exampleMilestoneRelease);
	}

	public void save(MilestoneRelease milestoneRelease) {
		milestoneReleaseAction.persist(milestoneRelease);
	}

}
