package com.revature.dtos;

/**
 * Data transfer object that is not persisted and is only meant to take the input from the authorization service. Has
 * all the same fields and methods as the version the Authorization service will send over.
 */
public class UserDTO {
    private int id;
    private String email;
    private String role;

    public UserDTO() {
    }

    public UserDTO(int id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
