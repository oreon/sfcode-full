package bizobjects;

import javax.persistence.*;

@MappedSuperclass
public class /*0 */Person extends org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private String firstName;

	private String lastName;

	private java.util.Date dob;

	@Column(nullable = false, unique = false)
	public String getFirstName() {
		return this.firstName;
	}

	@Column(nullable = false, unique = false)
	public String getLastName() {
		return this.lastName;
	}

	public java.util.Date getDob() {
		return this.dob;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDob(java.util.Date dob) {
		this.dob = dob;
	}

	private bizobjects.Address primaryAddress = new bizobjects.Address();

	public void setPrimaryAddress(bizobjects.Address primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public bizobjects.Address getPrimaryAddress() {
		return this.primaryAddress;
	}

}
