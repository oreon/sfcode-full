package com.nas.recovery.websvc.issues;

import javax.jws.WebService;
import org.wc.trackrite.issues.MilestoneRelease;
import java.util.List;

@WebService
public interface MilestoneReleaseWebService {

	public MilestoneRelease loadById(Long id);

	public List<MilestoneRelease> findByExample(
			MilestoneRelease exampleMilestoneRelease);

	public void save(MilestoneRelease milestoneRelease);

}
