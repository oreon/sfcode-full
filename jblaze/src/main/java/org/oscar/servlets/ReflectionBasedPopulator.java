package org.oscar.servlets;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;
import org.caisi.persistence.base.exceptions.BusinessException;
import org.caisi.persistence.base.exceptions.CriticalException;

/**
 * This class has functions that enable creation/updating properties of an
 * existing object
 * 
 * @author jsingh
 * 
 */
public class ReflectionBasedPopulator {

	private static final Logger logger = Logger
			.getLogger(ReflectionBasedPopulator.class);

	public static Object createBeanFromMap(Map<String, Object> fldValueMap,
			Class clz) {

		Object obj = null;
		try {
			obj = clz.newInstance();
			return createBeanFromMap(fldValueMap, obj);
		} catch (Exception e) {
			throw new BusinessException("An error ouccred creating bean "
					+ clz.getCanonicalName(), e);
		}

	}

	/**
	 * @param fldValueMap
	 * @param clz
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessExceptionAA
	 */
	public static Object createBeanFromMap(Map<String, Object> fldValueMap,
			Object obj) {

		String className = obj.getClass().getName();
		Set<String> keys = fldValueMap.keySet();

		for (String key : keys) {
			try {

				Object value = getValue(fldValueMap, key);
				logger.debug("setting property " + key + ":" + value);
				// for complex properties
				if (key.indexOf(".") >= 0) {
					String[] arr = key.split("\\.");

					Object complexProp = BeanUtils.getProperty(obj, arr[0]);

					if (!isNull(value)) {
						if (complexProp == null) {
							Field fld = obj.getClass().getDeclaredField(arr[0]);
							BeanUtils.copyProperty(obj, arr[0], fld.getType()
									.newInstance());
						}
						BeanUtils.copyProperty(obj, key, value);
					}
				} else
					BeanUtils.copyProperty(obj, key, value);
			} catch (Exception e) {
				throw new BusinessException("An error ouccred invoking field "
						+ key, e);
			}
		}

		return obj;

	}

	/**
	 * Useful for complex properties e.g. retrieving address.city on person - if
	 * address is null will return null
	 * 
	 * @param bean
	 * @param propertyName
	 * @return
	 */
	public static Object safeGetValue(Object bean, String propertyName) {
		try {
			if (propertyName.indexOf(".") >= 0) {
				String[] arr = propertyName.split("\\.");

				// BeanUtils.getProperty(bean, arr[0]);
				Field fld = bean.getClass().getDeclaredField(arr[0]);
			
					Method method = bean.getClass().getMethod("get" + 
							WordUtils.capitalize(fld.getName()),  null);
					Object complexProp = method.invoke(bean, null);
					if(complexProp == null)
						return null;
			}
			return BeanUtils.getProperty(bean, propertyName);
		} catch (Exception e) {
			throw new CriticalException(propertyName
					+ " couldnt be invoked - make sure property exists ", e);
		}
	}

	private static Object getValue(Map<String, Object> fldValueMap, String key) {
		Object value = fldValueMap.get(key);
		if (value != null && value instanceof String[]) {
			String[] arrVals = (String[]) value;
			return arrVals[0].length() == 0 ? null : arrVals[0];
		}

		return value;
	}

	private static void logWarn(String message) {
		System.out.println(message);
	}

	private static boolean isNull(Object obj) {
		if (obj instanceof String)
			return StringUtils.isEmpty((String) obj);
		return obj == null;
	}
}
