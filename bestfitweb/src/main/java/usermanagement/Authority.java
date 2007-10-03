package usermanagement;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class /*0 */Authority
		extends
			org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String authority;

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	private usermanagement.User user;

	public void setUser(usermanagement.User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name = "user_ID", nullable = false)
	public usermanagement.User getUser() {
		return this.user;
	}

}
