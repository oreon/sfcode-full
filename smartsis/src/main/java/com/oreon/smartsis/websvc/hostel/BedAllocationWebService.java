package com.oreon.smartsis.websvc.hostel;

import javax.jws.WebService;
import com.oreon.smartsis.hostel.BedAllocation;
import java.util.List;

@WebService
public interface BedAllocationWebService {

	public BedAllocation loadById(Long id);

	public List<BedAllocation> findByExample(BedAllocation exampleBedAllocation);

	public void save(BedAllocation bedAllocation);

}
