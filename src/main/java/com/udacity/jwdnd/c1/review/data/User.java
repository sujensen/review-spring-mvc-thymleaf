package com.udacity.jwdnd.c1.review.data;

/*
Holds the data the user entered via the User form, plus more (like our generated id and salt).
This is where the user's entered password gets encrypted: after the Controller, but before the Mapper.
Each user will have a unique salt.
 */
public class User {
    private Integer userId;
    private String username;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;

    public User(Integer userId, String username, String salt, String password, String firstName, String lastName) {
        this.userId = userId;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getUserId() {

        return userId;
    }

    public String getUsername() {

        return username;
    }

    public String getSalt() {

        return salt;
    }

    public String getPassword() {

        return password;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setUserId(Integer userId) {

        this.userId = userId;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public void setSalt(String salt) {

        this.salt = salt;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }
}
