package prj321x.assignment2.backend.entities.recruitments;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link Recruitment}
 */
public record RecruitmentDto(UUID id, Set<UUID> categoryIds, String title, String address, UUID companyId,
                             Set<UUID> recruitmentApplyIds) implements Serializable {
}