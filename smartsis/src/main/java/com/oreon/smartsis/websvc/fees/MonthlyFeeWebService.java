package com.oreon.smartsis.websvc.fees;

import javax.jws.WebService;
import com.oreon.smartsis.fees.MonthlyFee;
import java.util.List;

@WebService
public interface MonthlyFeeWebService {

	public MonthlyFee loadById(Long id);

	public List<MonthlyFee> findByExample(MonthlyFee exampleMonthlyFee);

	public void save(MonthlyFee monthlyFee);

}
