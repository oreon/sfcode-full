package com.oreon.smartsis.websvc.fees;

import javax.jws.WebService;
import com.oreon.smartsis.fees.GradeFeeItem;
import java.util.List;

@WebService
public interface GradeFeeItemWebService {

	public GradeFeeItem loadById(Long id);

	public List<GradeFeeItem> findByExample(GradeFeeItem exampleGradeFeeItem);

	public void save(GradeFeeItem gradeFeeItem);

}
