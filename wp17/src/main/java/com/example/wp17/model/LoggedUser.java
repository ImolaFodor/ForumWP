package com.example.wp17.model;

import java.io.Serializable;

public class LoggedUser implements Serializable {
	
    private String username;

	public LoggedUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    
}
