package com.oreon.smartsis.websvc.hostel;

import javax.jws.WebService;
import com.oreon.smartsis.hostel.Hostel;
import java.util.List;

@WebService
public interface HostelWebService {

	public Hostel loadById(Long id);

	public List<Hostel> findByExample(Hostel exampleHostel);

	public void save(Hostel hostel);

}
