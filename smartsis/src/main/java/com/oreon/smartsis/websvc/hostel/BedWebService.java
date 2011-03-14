package com.oreon.smartsis.websvc.hostel;

import javax.jws.WebService;
import com.oreon.smartsis.hostel.Bed;
import java.util.List;

@WebService
public interface BedWebService {

	public Bed loadById(Long id);

	public List<Bed> findByExample(Bed exampleBed);

	public void save(Bed bed);

	public List availableBeds();

}
