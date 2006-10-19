package aero.sita.ads.framework.apis2;

import java.util.HashMap;
import java.util.Map;

/**
 * This is an abstract implentation of APISInfoComponent to facilitate management of collection
 * of missing fields
 * 
 * @author jsingh
 *
 */
public abstract class AbstractAPISInfoComponent implements APISInfoComponent {
	
	private Map missingFields = new HashMap();

	public void addMissingField(String field) {
		missingFields.put(field, "");
	}

	public Map getMissingFields() {
		return missingFields;
	}
	
}
