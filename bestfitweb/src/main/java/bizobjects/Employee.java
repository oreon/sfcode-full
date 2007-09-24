package bizobjects;

import javax.persistence.*;

import org.witchcraft.model.support.User;


@Entity
public class Employee extends Person implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    
    
    private User userAccount;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userId", nullable=false)
    public User getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(User userAccount) {
		this.userAccount = userAccount;
	}

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    //*****Done Implementing interface User ****
}
