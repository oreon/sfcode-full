package com.oreon.smartsis.web.action.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;
import org.witchcraft.base.entity.BaseQuery;
import org.witchcraft.base.entity.Range;

import org.jboss.seam.annotations.Observer;

import com.oreon.smartsis.domain.Settings;

@Name("settingsList")
//@Scope(ScopeType.SESSION)
public class SettingsListQuery extends SettingsListQueryBase implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4307319305350668105L;

	private Settings defaultSettings;

	public Settings getDefaultSettings() {
		if (defaultSettings == null) {
			List<Settings> mysettings = getResultList();
			if (!mysettings.isEmpty())
				defaultSettings = getResultList().get(0);
		}
		return defaultSettings;
	}

	public void setDefaultSettings(Settings defaultSettings) {
		this.defaultSettings = defaultSettings;
	}
}
