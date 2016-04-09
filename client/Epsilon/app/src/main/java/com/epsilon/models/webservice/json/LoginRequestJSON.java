package com.epsilon.models.webservice.json;

/**
 * Created by Dandoh on 4/9/16.
 */
public class LoginRequestJSON {
    String username;
    String password;

    public LoginRequestJSON(String username, String password) {
        this.username = username;
        this.password = password;
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
}
