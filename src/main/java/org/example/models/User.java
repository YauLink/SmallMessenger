package org.example.models;

public class User {
    private String login;
    private String password;
    private String fullName;
    private String dateOfBirth;

    public User(String login, String password, String fullName, String dateOfBirth) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    public Object getPassword() {
        return this.password;
    }

    public String getLogin() {
        return this.login;
    }
}
