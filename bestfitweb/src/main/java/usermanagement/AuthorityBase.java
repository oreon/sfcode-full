
/**
 * This is generated code - to edit code or override methods use - Authority class
 * @author witchCraft Code Generator
 * WARNING  - DO NOT EDIT - CHANGES WILL BE OVERWRITTEN
 */

package usermanagement;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import java.util.Date;

@MappedSuperclass
/*@Entity
@Table(name="authorities",uniqueConstraints={@UniqueConstraint(columnNames={})})*/
public abstract class AuthorityBase
		extends
			org.witchcraft.model.support.security.AbstractAuthority
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	protected String authority;

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

	public abstract Authority authorityInstance();

}
