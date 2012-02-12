package com.hrb.tservices.websvc.faq;

import javax.jws.WebService;
import com.hrb.tservices.domain.faq.FaqCategory;
import java.util.List;

@WebService
public interface FaqCategoryWebService {

	public FaqCategory loadById(Long id);

	public List<FaqCategory> findByExample(FaqCategory exampleFaqCategory);

	public void save(FaqCategory faqCategory);

}
