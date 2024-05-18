package prj321x.assignment2.backend.entities.recruitments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface RecruitmentRepository extends JpaRepository<Recruitment, UUID> {
    @Query("select r from Recruitment r join r.categories rc " +
            "where rc.id = :category " +
            "order by size(r.recruitmentApplies)")
    Optional<Recruitment> findMostAppliedRecruitmentByCategory(@Param("category") UUID category);
}