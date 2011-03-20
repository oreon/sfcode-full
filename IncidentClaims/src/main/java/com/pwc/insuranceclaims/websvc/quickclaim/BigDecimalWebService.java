package com.pwc.insuranceclaims.websvc.quickclaim;

import javax.jws.WebService;
import com.pwc.insuranceclaims.quickclaim.BigDecimal;
import java.util.List;

@WebService
public interface BigDecimalWebService {

	public BigDecimal loadById(Long id);

	public List<BigDecimal> findByExample(BigDecimal exampleBigDecimal);

	public void save(BigDecimal bigDecimal);

}
