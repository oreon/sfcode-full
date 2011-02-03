package com.nas.recovery.websvc.issues;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.issues.Issue;

@WebService(endpointInterface = "com.nas.recovery.websvc.issues.IssueWebService", serviceName = "IssueWebService")
@Name("issueWebService")
public class IssueWebServiceImpl implements IssueWebService {

	@In(create = true)
	com.nas.recovery.web.action.issues.IssueAction issueAction;

	public Issue loadById(Long id) {
		return issueAction.loadFromId(id);
	}

	public List<Issue> findByExample(Issue exampleIssue) {
		return issueAction.search(exampleIssue);
	}

	public void save(Issue issue) {
		issueAction.persist(issue);
	}

}
