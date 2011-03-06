package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.Fee;
import java.util.List;

@WebService
public interface FeeWebService {

	public Fee loadById(Long id);

	public List<Fee> findByExample(Fee exampleFee);

	public void save(Fee fee);

}
