package bizobjects;

import javax.persistence.*;

@Entity
public class /*0 */Employee extends Person implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int code;

	@Column(nullable = false, unique = true)
	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
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

}
