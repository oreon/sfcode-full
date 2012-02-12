package com.hrb.tservices.websvc.faq;

import javax.jws.WebService;
import com.hrb.tservices.domain.faq.Rating;
import java.util.List;

@WebService
public interface RatingWebService {

	public Rating loadById(Long id);

	public List<Rating> findByExample(Rating exampleRating);

	public void save(Rating rating);

}
