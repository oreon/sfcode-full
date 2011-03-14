package com.oreon.smartsis.websvc.hostel;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.hostel.BedAllocation;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.hostel.BedAllocationWebService", serviceName = "BedAllocationWebService")
@Name("bedAllocationWebService")
public class BedAllocationWebServiceImpl implements BedAllocationWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.hostel.BedAllocationAction bedAllocationAction;

	public BedAllocation loadById(Long id) {
		return bedAllocationAction.loadFromId(id);
	}

	public List<BedAllocation> findByExample(BedAllocation exampleBedAllocation) {
		return bedAllocationAction.search(exampleBedAllocation);
	}

	public void save(BedAllocation bedAllocation) {
		bedAllocationAction.persist(bedAllocation);
	}

}
