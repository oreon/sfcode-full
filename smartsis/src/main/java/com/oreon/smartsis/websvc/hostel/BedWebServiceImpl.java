package com.oreon.smartsis.websvc.hostel;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.hostel.Bed;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.hostel.BedWebService", serviceName = "BedWebService")
@Name("bedWebService")
public class BedWebServiceImpl implements BedWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.hostel.BedAction bedAction;

	public Bed loadById(Long id) {
		return bedAction.loadFromId(id);
	}

	public List<Bed> findByExample(Bed exampleBed) {
		return bedAction.search(exampleBed);
	}

	public void save(Bed bed) {
		bedAction.persist(bed);
	}

	public List<com.oreon.smartsis.hostel.Bed> availableBeds() {
		return bedAction.availableBeds();
	}

}
