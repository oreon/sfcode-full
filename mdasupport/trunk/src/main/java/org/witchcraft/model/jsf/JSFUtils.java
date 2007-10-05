package org.witchcraft.model.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.witchcraft.model.support.BusinessEntity;

/** A utility class with convenience commonly used JSF routines
 * @author jsingh
 *
 */
public class JSFUtils {
	
	/** Takes a list of business entities and returns a list of 
	 *  SelectItems which can then be displayed in drop downs - 
	 *  the label is the displayName property and value is id 
	 * @param entities
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SelectItem> getAsSelectItems(List entities) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Object entity : entities) {
			BusinessEntity businessEntity = (BusinessEntity)entity;
			SelectItem item = new SelectItem(businessEntity , 
					businessEntity.getDisplayName());
			items.add(item);
		}
		return items;
	}

}
