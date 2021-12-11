package org.worldemunah.digiplax.api.model;

import java.io.Serializable;

/**
 * User: Ariel
 * Date: 5/8/2019
 */
public class UserDetailsModel implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String phoneNum;
    private Boolean isSuperUser;

    protected UserDetailsModel() {
    }

    public UserDetailsModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDetailsModel(Long id,
                            String firstName,
                            String lastName,
                            String emailAddress,
                            String password,
                            String phoneNum,
                            Boolean isSuperUser) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phoneNum = phoneNum;
        this.isSuperUser = isSuperUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Boolean isSuperUser() {
        return isSuperUser;
    }

    public void setSuperUser(Boolean superUser) {
        isSuperUser = superUser;
    }

    @Override
    public String toString() {
        return "UserDetailsModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", isSuperUser=" + isSuperUser +
                '}';
    }
}