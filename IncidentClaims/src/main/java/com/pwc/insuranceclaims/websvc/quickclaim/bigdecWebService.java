package com.pwc.insuranceclaims.websvc.quickclaim;

import javax.jws.WebService;
import com.pwc.insuranceclaims.quickclaim.bigdec;
import java.util.List;

@WebService
public interface bigdecWebService {

	public bigdec loadById(Long id);

	public List<bigdec> findByExample(bigdec examplebigdec);

	public void save(bigdec bigdec);

}
