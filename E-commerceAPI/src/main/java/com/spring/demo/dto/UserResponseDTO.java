package com.spring.demo.dto;

public class UserResponseDTO {

    private String username;
    private String role;

    public UserResponseDTO(String username, String role) {
        this.username = username;
        this.role = role;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    
}

