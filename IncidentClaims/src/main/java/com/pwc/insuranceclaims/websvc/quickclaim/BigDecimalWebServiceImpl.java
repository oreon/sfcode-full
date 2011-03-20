package com.pwc.insuranceclaims.websvc.quickclaim;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.pwc.insuranceclaims.quickclaim.BigDecimal;

@WebService(endpointInterface = "com.pwc.insuranceclaims.websvc.quickclaim.BigDecimalWebService", serviceName = "BigDecimalWebService")
@Name("bigDecimalWebService")
public class BigDecimalWebServiceImpl implements BigDecimalWebService {

	@In(create = true)
	com.pwc.insuranceclaims.web.action.quickclaim.BigDecimalAction bigDecimalAction;

	public BigDecimal loadById(Long id) {
		return bigDecimalAction.loadFromId(id);
	}

	public List<BigDecimal> findByExample(BigDecimal exampleBigDecimal) {
		return bigDecimalAction.search(exampleBigDecimal);
	}

	public void save(BigDecimal bigDecimal) {
		bigDecimalAction.persist(bigDecimal);
	}

}
