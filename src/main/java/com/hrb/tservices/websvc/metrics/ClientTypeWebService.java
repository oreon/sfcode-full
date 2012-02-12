package com.hrb.tservices.websvc.metrics;

import javax.jws.WebService;
import com.hrb.tservices.domain.metrics.ClientType;
import java.util.List;

@WebService
public interface ClientTypeWebService {

	public ClientType loadById(Long id);

	public List<ClientType> findByExample(ClientType exampleClientType);

	public void save(ClientType clientType);

}
