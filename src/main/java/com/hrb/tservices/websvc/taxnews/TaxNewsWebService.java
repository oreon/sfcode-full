package com.hrb.tservices.websvc.taxnews;

import javax.jws.WebService;
import com.hrb.tservices.domain.taxnews.TaxNews;
import java.util.List;

@WebService
public interface TaxNewsWebService {

	public TaxNews loadById(Long id);

	public List<TaxNews> findByExample(TaxNews exampleTaxNews);

	public void save(TaxNews taxNews);

}
