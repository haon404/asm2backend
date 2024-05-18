package prj321x.assignment2.backend.entities.recruitments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface RecruitmentRepository extends JpaRepository<Recruitment, UUID> {
//    TODO: Create find most applied recruitment with most popular category
    @Query("select r from Recruitment r order by size(r.recruitmentApplies) limit 1")
    Optional<Recruitment> findMostAppliedRecruitment();
}