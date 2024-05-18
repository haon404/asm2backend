package prj321x.assignment2.backend.entities.categories;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link Category}
 */
public record CategoryDto(UUID id, String name, Set<UUID> recruitmentIds) implements Serializable {
}