package prj321x.assignment2.backend.entities.recruitments;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import prj321x.assignment2.backend.entities.categories.Category;
import prj321x.assignment2.backend.entities.companies.Company;
import prj321x.assignment2.backend.entities.recruitments.applies.RecruitmentApply;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "recruitment")
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    
    @ManyToMany(mappedBy = "recruitments")
    private Set<Category> categories = new LinkedHashSet<>();
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "address", nullable = false)
    private String address;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
    
    
    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private Set<RecruitmentApply> recruitmentApplies = new LinkedHashSet<>();
    
}