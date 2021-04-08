package com.revature.dtos;

/**
 * Data transfer object that is not persisted and is only meant to take the input from the authorization service. Has
 * all the same fields and methods as the version the Authorization service will send over.
 */
public class UserDTO {
    private final int id;
    private final String email;
    private final String role;

    public UserDTO(int id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
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
