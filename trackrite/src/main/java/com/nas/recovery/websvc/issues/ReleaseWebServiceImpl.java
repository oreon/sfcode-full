package com.nas.recovery.websvc.issues;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.issues.Release;

@WebService(endpointInterface = "com.nas.recovery.websvc.issues.ReleaseWebService", serviceName = "ReleaseWebService")
@Name("releaseWebService")
public class ReleaseWebServiceImpl implements ReleaseWebService {

	@In(create = true)
	com.nas.recovery.web.action.issues.ReleaseAction releaseAction;

	public Release loadById(Long id) {
		return releaseAction.loadFromId(id);
	}

	public List<Release> findByExample(Release exampleRelease) {
		return releaseAction.search(exampleRelease);
	}

	public void save(Release release) {
		releaseAction.persist(release);
	}

}
