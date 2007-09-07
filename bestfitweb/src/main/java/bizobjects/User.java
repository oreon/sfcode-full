package bizobjects;

public interface User {
    @Transient
    public String getUserId();

    @Transient
    public String getPassword();

    @Transient
    public String getRole();
}
