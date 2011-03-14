package com.oreon.smartsis.websvc.hostel;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.hostel.Hostel;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.hostel.HostelWebService", serviceName = "HostelWebService")
@Name("hostelWebService")
public class HostelWebServiceImpl implements HostelWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.hostel.HostelAction hostelAction;

	public Hostel loadById(Long id) {
		return hostelAction.loadFromId(id);
	}

	public List<Hostel> findByExample(Hostel exampleHostel) {
		return hostelAction.search(exampleHostel);
	}

	public void save(Hostel hostel) {
		hostelAction.persist(hostel);
	}

}
