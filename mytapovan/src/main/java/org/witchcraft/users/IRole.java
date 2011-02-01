package org.witchcraft.users;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;

@Entity
@Table(name = "role")
@Filter(name = "archiveFilterDef")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public interface IRole {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    public abstract Long getId();

    public abstract void setId(Long id);

	public abstract void setName(String name);

	public abstract String getName();

	public abstract void setUsers(Set<IUser> users);

	@ManyToMany(mappedBy = "roles")
	public abstract Set<IUser> getUsers();

	@Transient
	public abstract String getDisplayName();

	//Empty setter , needed for richfaces autocomplete to work 
	public abstract void setDisplayName(String name);

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BusinessEntity#retrieveSearchableFieldsArray()
	 */
	public abstract List<String> listSearchableFields();
	
	
    public abstract Boolean getArchived();
    
	public abstract void setArchived(boolean archived);


}