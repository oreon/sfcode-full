package com.oreon.smartsis.websvc.domain;
import javax.jws.WebService;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.domain.Settings;

@WebService(endpointInterface = "com.oreon.smartsis.websvc.domain.SettingsWebService", serviceName = "SettingsWebService")
@Name("settingsWebService")
public class SettingsWebServiceImpl implements SettingsWebService {

	@In(create = true)
	com.oreon.smartsis.web.action.domain.SettingsAction settingsAction;

	public Settings loadById(Long id) {
		return settingsAction.loadFromId(id);
	}

	public List<Settings> findByExample(Settings exampleSettings) {
		return settingsAction.search(exampleSettings);
	}

	public void save(Settings settings) {
		settingsAction.persist(settings);
	}

}
