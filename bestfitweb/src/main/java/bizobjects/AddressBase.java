
/**
 * This is generated code - to edit code or override methods use - Address class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package bizobjects;

import javax.persistence.*;

@MappedSuperclass
public abstract class AddressBase implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String streetAddress;

	protected String city;

	protected String zip;

	protected String email;

	protected String country;

	protected String state;

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

	public String getCountry() {
		return this.country;
	}

	public String getState() {
		return this.state;
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

	public void setCountry(String country) {
		this.country = country;
	}

	public void setState(String state) {
		this.state = state;
	}

	public abstract Address addressInstance();

}
