package usermanagement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class /*0 */User extends org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	@Column(nullable = false, unique = true)
	public String getUsername() {
		return this.username;
	}

	@Column(nullable = false, unique = false)
	public String getPassword() {
		return this.password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
