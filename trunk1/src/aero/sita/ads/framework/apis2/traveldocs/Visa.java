package aero.sita.ads.framework.apis2.traveldocs;

import sita.ads.apx.framework.businesslogic.BLConstants;

public class Visa extends TravelDocument {

	public Integer getType() {
		return new Integer(BLConstants.TRAVEL_DOC_TYPE_VISA);
	}

	public String getPrefix() {
		return null;
	}

}
