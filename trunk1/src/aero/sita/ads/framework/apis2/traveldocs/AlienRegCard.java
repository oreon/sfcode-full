package aero.sita.ads.framework.apis2.traveldocs;

import sita.ads.apx.framework.businesslogic.BLConstants;

public class AlienRegCard extends TravelDocument{
	
	private static final String NAME = "AlienReg";

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
		return NAME;
	}

}
