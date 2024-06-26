package prj321x.assignment2.backend.entities.companies;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link Company}
 */
public record CompanyDto(UUID id, String name, Set<UUID> recruitmentIds) implements Serializable {
}