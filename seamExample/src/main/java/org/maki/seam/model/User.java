package org.maki.seam.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.validator.Email;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@EntityListeners({UserListener.class})
@Table(name="User")
public class User implements Serializable {
    
    private static final long serialVersionUID = 7457197149421634123L;

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    private Long userId;
    
    @Version
    @Column(name="version")
    private Long version;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_created")
    private Date dateCreated;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_updated")
    private Date dateUpdated;
    
    @NotNull
    @Column(name="first_name")
    private String firstName;
    
    @NotNull
    @Column(name="last_name")
    private String lastName;
    
    @NotNull @Email @Column(name="email_address", unique=true)
    private String emailAddress;
    

    @NotNull @Length(min=6, max=16)
    @Column(name="password")
    private String password;

    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="users")
    private Set<Role> roles = new HashSet<Role>();
    
    public User() {
        super();
    }

    public User(Date dateCreated,
            Date dateUpdated, String emailAddress,
            String firstName, Long id, String lastName,
            String password,
            Long version) {
        super();
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.userId = id;
        this.lastName = lastName;
        this.password = password;
        this.version = version;
    }

    public String getName() {
      return firstName + " " + lastName;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

    protected void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (! (o instanceof User)) return false;
        
        final User other = (User)o;
        if (emailAddress == null) {
            if (other.getEmailAddress() != null) {
                return false;
            }
        } else if (!emailAddress.equals(other.getEmailAddress())) {
            return false;
        }
        
        return true; 
        
    }
    @Override
    public String toString() {
        // TODO: add toString impl
        
        return super.toString();
    }
}
