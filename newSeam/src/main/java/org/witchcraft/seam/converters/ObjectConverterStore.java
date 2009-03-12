package org.witchcraft.seam.converters;

import java.rmi.server.UID;
import java.util.HashMap;
import java.util.Map;

import org.witchcraft.base.entity.BusinessEntity;



public class ObjectConverterStore {

	private final Map<String, Object> objects = new HashMap<String, Object>();

	public String put(Object entity) {
		String key = null;
		if (entity instanceof BusinessEntity) {
			key = getEntityKey((BusinessEntity)entity);
		}
        if (key==null)   {
            key = new UID().toString();
		}
		objects.put(key, entity);
		return key;
	}

	public Object get(String key) {
		return objects.get(key);
	}

	private String getEntityKey(BusinessEntity entity) {
		if (entity.getId() == null){
            return  null;
        }
		return entity.getId().toString();
	}

}
