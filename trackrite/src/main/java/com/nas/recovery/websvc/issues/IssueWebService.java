package com.nas.recovery.websvc.issues;

import javax.jws.WebService;
import org.wc.trackrite.issues.Issue;
import java.util.List;

@WebService
public interface IssueWebService {

	public Issue loadById(Long id);

	public List<Issue> findByExample(Issue exampleIssue);

	public void save(Issue issue);

}
