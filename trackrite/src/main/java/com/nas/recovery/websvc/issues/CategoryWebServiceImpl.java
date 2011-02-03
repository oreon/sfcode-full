package com.nas.recovery.websvc.issues;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import org.wc.trackrite.issues.Category;

@WebService(endpointInterface = "com.nas.recovery.websvc.issues.CategoryWebService", serviceName = "CategoryWebService")
@Name("categoryWebService")
public class CategoryWebServiceImpl implements CategoryWebService {

	@In(create = true)
	com.nas.recovery.web.action.issues.CategoryAction categoryAction;

	public Category loadById(Long id) {
		return categoryAction.loadFromId(id);
	}

	public List<Category> findByExample(Category exampleCategory) {
		return categoryAction.search(exampleCategory);
	}

	public void save(Category category) {
		categoryAction.persist(category);
	}

}
