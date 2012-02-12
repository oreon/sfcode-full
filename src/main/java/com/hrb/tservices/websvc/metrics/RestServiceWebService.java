package com.hrb.tservices.websvc.metrics;

import javax.jws.WebService;
import com.hrb.tservices.domain.metrics.RestService;
import java.util.List;

@WebService
public interface RestServiceWebService {

	public RestService loadById(Long id);

	public List<RestService> findByExample(RestService exampleRestService);

	public void save(RestService restService);

}
