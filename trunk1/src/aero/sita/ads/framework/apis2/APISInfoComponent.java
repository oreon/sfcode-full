package aero.sita.ads.framework.apis2;

import java.util.Map;

/**
 * The interface that is to be implemented by any class that intends to be part of APISInfo
 * @author jsingh
 *
 */
public interface APISInfoComponent {
	
	public String getPrefix();
	public Map getMissingFields();
	public void addMissingField(String field);  //TODO: change to enum
}
