package prj321x.assignment2.backend.controllers;

import java.io.Serializable;

/**
 * DTO for {@link prj321x.assignment2.backend.entities.users.User}
 */
public record RegisterDto(String roleRoleName, String email, String fullName, String password) implements Serializable {
}