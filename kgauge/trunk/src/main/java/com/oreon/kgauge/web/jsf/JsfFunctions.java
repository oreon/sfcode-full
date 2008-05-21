package com.oreon.kgauge.web.jsf;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

/**
 * Functions to aid developing JSF applications.
 */
public final class JsfFunctions {
	
	private static final Logger logger = Logger.getLogger(JsfFunctions.class);
	
	/**
	 * Stops creation of a new JsfFunctions object.
	 */
	private JsfFunctions() {
	}

	/**
	 * Get the field label.
	 *
	 * @param fieldName
	 *            fieldName
	 * @param formId
	 *            form id
	 * @return Message from the Message Source.
	 */
	public static String getFieldLabel(final String fieldName,
			final String formId) {

		ResourceBundle bundle = getCurrentResourceBundle();

		/** Look for formId.fieldName, e.g., EmployeeForm.firstName. */

		String label = null;
		try {
			label = bundle.getString(formId + fieldName);
			return label;
		} catch (MissingResourceException e) {
			// do nothing on purpose.
		}

		try {
			/** Look for just fieldName, e.g., firstName. */
			label = bundle.getString(fieldName);
		} catch (MissingResourceException e) {
			/**
			 * Convert fieldName, e.g., firstName automatically becomes First
			 * Name.
			 */
			label = generateLabelValue(fieldName);
		}

		return label;

	}
	
	public static String getMessageFromBundle(String key, Object[] params){
		String result = null;
		try {
			result = getCurrentResourceBundle().getString(key);
			if(params != null){
				MessageFormat messageFormat = new MessageFormat(result);
				result = messageFormat.format(params, new StringBuffer(), null).toString();
			}
			return result;
		} catch (MissingResourceException e) {
			// do nothing on purpose.
			logger.warn("Key: " + key + " not found in resource bundle");
			return key;
		}
		
	}

	private static ResourceBundle getCurrentResourceBundle() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot()
				.getLocale();
		String bundleName = FacesContext.getCurrentInstance().getApplication()
				.getMessageBundle();

		ResourceBundle bundle = ResourceBundle
                                        .getBundle(bundleName, locale, getClassLoader());
		return bundle;
	}

	private static ClassLoader getClassLoader() {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		if (classLoader == null) {
			return JsfFunctions.class.getClassLoader();
		}
		return classLoader;
	}

	/**
	 * Generate the field. Transforms firstName into First Name. This allows
	 * reasonable defaults for labels.
	 *
	 * @param fieldName
	 *            fieldName
	 *
	 * @return generated label name.
	 */
	public static String generateLabelValue(final String fieldName) {
		StringBuffer buffer = new StringBuffer(fieldName.length() * 2);
		char[] chars = fieldName.toCharArray();

		/* Change firstName to First Name. */
		for (int index = 0; index < chars.length; index++) {
			char cchar = chars[index];

			/* Make the first character uppercase. */
			if (index == 0) {
				cchar = Character.toUpperCase(cchar);
				buffer.append(cchar);

				continue;
			}

			/* Look for an uppercase character, if found add a space. */
			if (Character.isUpperCase(cchar)) {
				buffer.append(' ');
				buffer.append(cchar);

				continue;
			}

			buffer.append(cchar);
		}

		return buffer.toString();
	}
}