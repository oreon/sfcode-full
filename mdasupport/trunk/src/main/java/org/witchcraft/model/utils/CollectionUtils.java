package org.witchcraft.model.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * This class is for applying operations such as sum,avg, min, max etc on
 * collections
 * 
 * @author jesing
 * 
 */
public class CollectionUtils {

	public static Integer sum(Collection collection, Method method) {
		
		Integer total = 0;
		for (Object object : collection) {
			try {
				total += (Integer) (method.invoke(object));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return total;
	}

}
