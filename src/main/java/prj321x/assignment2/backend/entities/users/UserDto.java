package prj321x.assignment2.backend.entities.users;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link User}
 */
public record UserDto(UUID id, String roleRoleName, String email, String password,
                      Set<UUID> recruitmentApplyIds) implements Serializable {
}