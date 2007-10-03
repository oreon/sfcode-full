package bizobjects;

import javax.persistence.*;

@Embeddable
public class /*0 */Address implements java.io.Serializable {

	private String streetAddress;

	private String city;

	private String zip;

	private String email;

	public String getStreetAddress() {
		return this.streetAddress;
	}

	public String getCity() {
		return this.city;
	}

	public String getZip() {
		return this.zip;
	}

	@Column(nullable = false, unique = true)
	public String getEmail() {
		return this.email;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
