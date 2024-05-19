package prj321x.assignment2.backend.entities.companies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    @Query("select c from Company c order by size(c.recruitments) limit 1")
    Optional<Company> findByMostRecruitment();
}