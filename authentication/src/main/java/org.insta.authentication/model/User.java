package org.insta.authentication.model;

/**
 * <p>
 * Hold the information about the user.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public class User {

    private int userId;
    private String name;
    private String mobileNumber;
    private String email;
    private String password;
    private final Address address;

    public User() {
        this.address = new Address();
    }

    public Address getAddress() {
        return address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(final String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String userName) {
        this.name = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return String.join(" ","userName : ", this.name
        , "\nmobileNumber : " , this.mobileNumber
        , "\nemail : ", this.email
        , this.address.toString());
    }
}
