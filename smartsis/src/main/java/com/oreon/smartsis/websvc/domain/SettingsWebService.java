package com.oreon.smartsis.websvc.domain;

import javax.jws.WebService;
import com.oreon.smartsis.domain.Settings;
import java.util.List;

@WebService
public interface SettingsWebService {

	public Settings loadById(Long id);

	public List<Settings> findByExample(Settings exampleSettings);

	public void save(Settings settings);

}
