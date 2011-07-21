package com.oreon.smartsis.websvc.fees;

import javax.jws.WebService;
import com.oreon.smartsis.fees.FeeItem;
import java.util.List;

@WebService
public interface FeeItemWebService {

	public FeeItem loadById(Long id);

	public List<FeeItem> findByExample(FeeItem exampleFeeItem);

	public void save(FeeItem feeItem);

}
