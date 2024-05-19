package prj321x.assignment2.backend.entities.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record LoginDto(@NotNull @NotBlank String email,
                       @NotNull @NotEmpty @NotBlank String password) implements Serializable {
}