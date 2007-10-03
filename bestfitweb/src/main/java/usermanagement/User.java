package usermanagement;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class /*0 */User extends org.witchcraft.model.support.BusinessEntity
		implements
			java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private boolean enabled = true;

	@Column(nullable = false, unique = true)
	public String getUsername() {
		return this.username;
	}

	@Column(nullable = false, unique = false)
	public String getPassword() {
		return this.password;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private java.util.Set<usermanagement.Authority> userAuthorities = new java.util.HashSet<usermanagement.Authority>();

	public void addUserAuthorities(usermanagement.Authority userAuthorities) {

		userAuthorities.setUser(this);

		this.userAuthorities.add(userAuthorities);
	}

	public void removeUserAuthorities(usermanagement.Authority userAuthorities) {
		this.userAuthorities.remove(userAuthorities);
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public java.util.Set<usermanagement.Authority> getUserAuthorities() {
		return this.userAuthorities;
	}

	public void setUserAuthorities(
			java.util.Set<usermanagement.Authority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}

	@Transient
	public java.util.Iterator<usermanagement.Authority> getUserAuthoritiesIterator() {
		return this.userAuthorities.iterator();
	}

}
