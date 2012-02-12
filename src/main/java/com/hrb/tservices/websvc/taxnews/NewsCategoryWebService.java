package com.hrb.tservices.websvc.taxnews;

import javax.jws.WebService;
import com.hrb.tservices.domain.taxnews.NewsCategory;
import java.util.List;

@WebService
public interface NewsCategoryWebService {

	public NewsCategory loadById(Long id);

	public List<NewsCategory> findByExample(NewsCategory exampleNewsCategory);

	public void save(NewsCategory newsCategory);

}
