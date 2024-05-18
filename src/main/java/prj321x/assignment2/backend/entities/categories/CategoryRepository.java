package prj321x.assignment2.backend.entities.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("select c from Category c join c.recruitments cr order by size(cr.recruitmentApplies) limit 1")
    Optional<Category> findCategoryByMostPopular();
}