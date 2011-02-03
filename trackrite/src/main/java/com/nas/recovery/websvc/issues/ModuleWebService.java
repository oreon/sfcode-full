package com.nas.recovery.websvc.issues;

import javax.jws.WebService;
import org.wc.trackrite.issues.Module;
import java.util.List;

@WebService
public interface ModuleWebService {

	public Module loadById(Long id);

	public List<Module> findByExample(Module exampleModule);

	public void save(Module module);

}
