package com.example.wp17.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Imola on 6/2/2017.
 */
public class User implements Serializable {
    private String username;
    private String name;
    private String lastName;
    private String password;
    private String role;
    private String phone;
    private String email;
    private String registrationDate;
    private List<String> followedSubForums= new ArrayList<String>();
    private List<String> createdTopics= new ArrayList<String>();
    private List<String> createdComments= new ArrayList<String>();
    
    

    public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<String> getFollowedSubForums() {
        return followedSubForums;
    }

    public void setFollowedSubForums(List<String> followedSubForums) {
        this.followedSubForums = followedSubForums;
    }

    public List<String> getCreatedTopics() {
        return createdTopics;
    }

    public void setCreatedTopics(List<String> createdTopics) {
        this.createdTopics = createdTopics;
    }

    public List<String> getCreatedComments() {
        return createdComments;
    }

    public void setCreatedComments(List<String> createdComments) {
        this.createdComments = createdComments;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", followedSubForums=" + followedSubForums +
                ", createdTopics=" + createdTopics +
                ", createdComments=" + createdComments +
                '}';
    }
}
