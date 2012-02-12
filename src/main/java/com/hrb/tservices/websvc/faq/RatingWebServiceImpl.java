package com.hrb.tservices.websvc.faq;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.hrb.tservices.domain.faq.Rating;

@WebService(endpointInterface = "com.hrb.tservices.websvc.faq.RatingWebService", serviceName = "RatingWebService")
@Name("ratingWebService")
public class RatingWebServiceImpl implements RatingWebService {

	@In(create = true)
	com.hrb.tservices.web.action.faq.RatingAction ratingAction;

	public Rating loadById(Long id) {
		return ratingAction.loadFromId(id);
	}

	public List<Rating> findByExample(Rating exampleRating) {
		return ratingAction.search(exampleRating);
	}

	public void save(Rating rating) {
		ratingAction.persist(rating);
	}

}
