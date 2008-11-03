package org.maki.seam.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.Length;

@Entity
@Table(name="Role")
public class Role extends ModelBase {

    private static final long serialVersionUID = 3063938109291400535L;

    @Column(name="role_name", length=50)
    @Length(max=50, min=2)
    private String roleName;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", 
       joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
       inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    )
    private Set<User> users = new HashSet<User>();

    public Set<User> getUsers() {
        return Collections.unmodifiableSet(users);
    }
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException(
                    "You cannot add a null User to a Role!");
        }
        // check to see if the user is in the set of users
        if (users.contains(user)) {
            // if the user is already associated, just return
            return;
        }
        // establish the bi-directional relationships
        users.add(user);
        user.addRole(this);
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
}
