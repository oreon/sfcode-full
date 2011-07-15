package com.oreon.inventory.websvc.inventory;

import javax.jws.WebService;
import com.oreon.inventory.inventory.Godown;
import java.util.List;

@WebService
public interface GodownWebService {

	public Godown loadById(Long id);

	public List<Godown> findByExample(Godown exampleGodown);

	public void save(Godown godown);

}
