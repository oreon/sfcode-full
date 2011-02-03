package com.nas.recovery.websvc.issues;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.issues.Project;

@WebService(endpointInterface = "com.nas.recovery.websvc.issues.ProjectWebService", serviceName = "ProjectWebService")
@Name("projectWebService")
public class ProjectWebServiceImpl implements ProjectWebService {

	@In(create = true)
	com.nas.recovery.web.action.issues.ProjectAction projectAction;

	public Project loadById(Long id) {
		return projectAction.loadFromId(id);
	}

	public List<Project> findByExample(Project exampleProject) {
		return projectAction.search(exampleProject);
	}

	public void save(Project project) {
		projectAction.persist(project);
	}

}
