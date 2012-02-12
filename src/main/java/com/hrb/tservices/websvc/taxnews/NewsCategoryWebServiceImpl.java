package com.hrb.tservices.websvc.taxnews;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.taxnews.NewsCategory;

@WebService(endpointInterface = "com.hrb.tservices.websvc.taxnews.NewsCategoryWebService", serviceName = "NewsCategoryWebService")
@Name("newsCategoryWebService")
public class NewsCategoryWebServiceImpl implements NewsCategoryWebService {

	@In(create = true)
	com.hrb.tservices.web.action.taxnews.NewsCategoryAction newsCategoryAction;

	public NewsCategory loadById(Long id) {
		return newsCategoryAction.loadFromId(id);
	}

	public List<NewsCategory> findByExample(NewsCategory exampleNewsCategory) {
		return newsCategoryAction.search(exampleNewsCategory);
	}

	public void save(NewsCategory newsCategory) {
		newsCategoryAction.persist(newsCategory);
	}

}
