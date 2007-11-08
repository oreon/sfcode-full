
/**
 * This is generated code - to edit code or override methods use - RegisteredUser class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package bizobjects;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="RegisteredUser",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class RegisteredUserBase extends Person
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String gender;

	protected String image;

	public String getGender() {
		return this.gender;
	}

	@Column(nullable = false, unique = false)
	public String getImage() {

		return this.image;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setImage(String image) {
		this.image = image;
	}

	private usermanagement.User userAccount = new usermanagement.User();

	public void setUserAccount(usermanagement.User userAccount) {
		this.userAccount = userAccount;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userAccount_ID", nullable = false)
	public usermanagement.User getUserAccount() {
		return this.userAccount;
	}

	public abstract RegisteredUser registeredUserInstance();

	@Transient
	public String getDisplayName() {
		return lastName + ", " + firstName + "";
	}

}
