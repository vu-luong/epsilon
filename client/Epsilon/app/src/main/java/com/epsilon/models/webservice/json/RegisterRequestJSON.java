package com.epsilon.models.webservice.json;

/**
 * Created by Dandoh on 4/9/16.
 */
public class RegisterRequestJSON {

    String username;
    String password;
    String requirement_category;

    public RegisterRequestJSON(String username, String password, String requirement_category) {
        this.username = username;
        this.password = password;
        this.requirement_category = requirement_category;
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

    public String getRequirement_category() {
        return requirement_category;
    }

    public void setRequirement_category(String requirement_category) {
        this.requirement_category = requirement_category;
    }
}
