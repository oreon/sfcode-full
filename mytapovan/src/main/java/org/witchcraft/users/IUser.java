package org.witchcraft.users;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;

@Entity
@Table(name = "user")
@Filter(name = "archiveFilterDef")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public interface IUser {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    public abstract Long getId();

    public abstract void setId(Long id);

	public abstract void setUserName(String userName);

	public abstract String getUserName();

	public abstract void setPassword(String password);

	public abstract String getPassword();

	public abstract void setEnabled(Boolean enabled);

	public abstract Boolean getEnabled();

	public abstract void setRoles(Set<? extends IRole> roles);

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = org.witchcraft.users.Role.class)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "users_ID"), inverseJoinColumns = @JoinColumn(name = "roles_ID"))
	public abstract Set<? extends IRole> getRoles();

	public abstract void setEmail(String email);

	public abstract String getEmail();

	@Transient
	public abstract String getDisplayName();

	//Empty setter , needed for richfaces autocomplete to work 
	public abstract void setDisplayName(String name);

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	public abstract List<String> listSearchableFields();

}