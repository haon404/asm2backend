package prj321x.assignment2.backend.entities.companies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
    @Query("select c from Company c join c.recruitments r group by c.id order by count(r.id) limit 1")
    Optional<Company> findByMostRecruitment();
}