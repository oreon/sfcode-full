package com.nas.recovery.websvc.issues;

import javax.jws.WebService;
import org.wc.trackrite.issues.Project;
import java.util.List;

@WebService
public interface ProjectWebService {

	public Project loadById(Long id);

	public List<Project> findByExample(Project exampleProject);

	public void save(Project project);

}
