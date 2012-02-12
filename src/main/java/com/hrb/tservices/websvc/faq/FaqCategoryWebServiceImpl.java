package com.hrb.tservices.websvc.faq;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.faq.FaqCategory;

@WebService(endpointInterface = "com.hrb.tservices.websvc.faq.FaqCategoryWebService", serviceName = "FaqCategoryWebService")
@Name("faqCategoryWebService")
public class FaqCategoryWebServiceImpl implements FaqCategoryWebService {

	@In(create = true)
	com.hrb.tservices.web.action.faq.FaqCategoryAction faqCategoryAction;

	public FaqCategory loadById(Long id) {
		return faqCategoryAction.loadFromId(id);
	}

	public List<FaqCategory> findByExample(FaqCategory exampleFaqCategory) {
		return faqCategoryAction.search(exampleFaqCategory);
	}

	public void save(FaqCategory faqCategory) {
		faqCategoryAction.persist(faqCategory);
	}

}
