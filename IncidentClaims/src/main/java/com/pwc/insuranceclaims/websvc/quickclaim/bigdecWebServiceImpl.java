package com.pwc.insuranceclaims.websvc.quickclaim;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.pwc.insuranceclaims.quickclaim.bigdec;

@WebService(endpointInterface = "com.pwc.insuranceclaims.websvc.quickclaim.bigdecWebService", serviceName = "bigdecWebService")
@Name("bigdecWebService")
public class bigdecWebServiceImpl implements bigdecWebService {

	@In(create = true)
	com.pwc.insuranceclaims.web.action.quickclaim.bigdecAction bigdecAction;

	public bigdec loadById(Long id) {
		return bigdecAction.loadFromId(id);
	}

	public List<bigdec> findByExample(bigdec examplebigdec) {
		return bigdecAction.search(examplebigdec);
	}

	public void save(bigdec bigdec) {
		bigdecAction.persist(bigdec);
	}

}
