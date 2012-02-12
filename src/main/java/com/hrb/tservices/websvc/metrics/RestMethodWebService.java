package com.hrb.tservices.websvc.metrics;

import javax.jws.WebService;
import com.hrb.tservices.domain.metrics.RestMethod;
import java.util.List;

@WebService
public interface RestMethodWebService {

	public RestMethod loadById(Long id);

	public List<RestMethod> findByExample(RestMethod exampleRestMethod);

	public void save(RestMethod restMethod);

}
