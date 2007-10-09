package bizobjects;

import javax.persistence.*;

@MappedSuperclass
public abstract class PersonBase
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String firstName;

	protected String lastName;

	protected java.util.Date dob;

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

	public abstract Person personInstance();

}
