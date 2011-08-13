package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.ParentGroup;
import java.util.List;

@WebService
public interface ParentGroupWebService {

	public ParentGroup loadById(Long id);

	public List<ParentGroup> findByExample(ParentGroup exampleParentGroup);

	public void save(ParentGroup parentGroup);

}
