
/**
 * This is generated code - to edit code or override methods use - Person class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

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

	protected int age;

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

	@Transient
	public int getAge() {

		age = 0;
		age = org.witchcraft.model.utils.DateUtils.calcAge(dob);

		return this.age;
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

	public void setAge(int age) {
		this.age = age;
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
