package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.GradeFee;
import java.util.List;

@WebService
public interface GradeFeeWebService {

	public GradeFee loadById(Long id);

	public List<GradeFee> findByExample(GradeFee exampleGradeFee);

	public void save(GradeFee gradeFee);

}
