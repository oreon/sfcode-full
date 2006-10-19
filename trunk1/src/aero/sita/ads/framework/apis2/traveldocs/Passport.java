package aero.sita.ads.framework.apis2.traveldocs;

import aero.sita.ads.framework.apis2.APISInfoComponent;
import sita.ads.apx.framework.businesslogic.BLConstants;

public class Passport extends TravelDocument{

	private static final String PASSPORT = "Passport";

	/** (non-Javadoc)
	 * @see aero.sita.ads.framework.apis2.traveldocs.TravelDocument#getType()
	 */
	public Integer getType() {
		return new Integer(BLConstants.TRAVEL_DOC_TYPE_PASSPORT);
	}
	
	/**  
	 * @see aero.sita.ads.framework.apis2.APISInfoComponent#getPrefix()
	 */
	public String getPrefix() {
		return PASSPORT;
	}
	
}
