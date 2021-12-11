package org.worldemunah.digiplax.api.model.request;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * User: Ariel
 * Date: 3/12/2019
 */
public class AuthenticationRequest implements Serializable {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    protected AuthenticationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
