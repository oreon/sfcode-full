package bizobjects;

import javax.persistence.*;


@MappedSuperclass
public class Person extends org.witchcraft.model.support.BusinessEntity
    implements java.io.Serializable {
    private String firstName;
    private String lastName;
    private bizobjects.Address primaryAddress = new Address();

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPrimaryAddress(bizobjects.Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public bizobjects.Address getPrimaryAddress() {
        return this.primaryAddress;
    }
}
