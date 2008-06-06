package org.witchcraft.model.jsf;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.witchcraft.model.support.BusinessEntity;


/**
 * A utility class with convenience commonly used JSF routines
 * 
 * @author jsingh
 * 
 */
public final class JSFUtils {

	private static final Logger logger = Logger.getLogger(JSFUtils.class);

	/**
	 * Stops creation of a new JsfFunctions object.
	 */
	private JSFUtils() {
	}

	/** Gets the message from current locale's bundle, if not found re turns the key
	 * @param key
	 * @param params
	 * @return
	 */
	public static String getMessageFromBundle(String key, Object[] params) {
		String result = null;
		try {
			result = getCurrentResourceBundle().getString(key);
			if (params != null) {
				MessageFormat messageFormat = new MessageFormat(result);
				result = messageFormat.format(params, new StringBuffer(), null)
						.toString();
			}
			return result;
		} catch (MissingResourceException e) {
			//return the key and log a warning 
			logger.warn("Key: " + key + " not found in resource bundle");
			return key;
		}

	}

	private static ResourceBundle getCurrentResourceBundle() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot()
				.getLocale();
		String bundleName = FacesContext.getCurrentInstance().getApplication()
				.getMessageBundle();

		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale,
				getClassLoader());
		return bundle;
	}

	private static ClassLoader getClassLoader() {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader == null) {
			return JSFUtils.class.getClassLoader();
		}
		return classLoader;
	}

	/**
	 * Takes a list of business entities and returns a list of SelectItems which
	 * can then be displayed in drop downs - the label is the displayName
	 * property and value is id
	 * 
	 * @param entities
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SelectItem> getAsSelectItems(Collection entities) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Object entity : entities) {
			BusinessEntity businessEntity = (BusinessEntity) entity;
			SelectItem item = new SelectItem(businessEntity, businessEntity
					.getDisplayName());
			items.add(item);
		}
		return items;
	}

}
