package com.oreon.smartsis.websvc.fees;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.fees.MonthlyFee;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.fees.MonthlyFeeWebService", serviceName = "MonthlyFeeWebService")
@Name("monthlyFeeWebService")
public class MonthlyFeeWebServiceImpl implements MonthlyFeeWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.fees.MonthlyFeeAction monthlyFeeAction;

	public MonthlyFee loadById(Long id) {
		return monthlyFeeAction.loadFromId(id);
	}

	public List<MonthlyFee> findByExample(MonthlyFee exampleMonthlyFee) {
		return monthlyFeeAction.search(exampleMonthlyFee);
	}

	public void save(MonthlyFee monthlyFee) {
		monthlyFeeAction.persist(monthlyFee);
	}

}
