package aero.sita.ads.framework.apis2;



/**
 * 
 * @author jsingh
 *
 */
public class DestinationInfo extends AbstractAPISInfoComponent  {
	
	private static final String DESTINATION_INFO = "Destination Info";
	private String address;
	private String city;
	private String state;
	private String zip;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}


	public String getPrefix() {
		// TODO Auto-generated method stub
		return DESTINATION_INFO;
	}


}
