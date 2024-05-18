package prj321x.assignment2.backend.entities.companies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import prj321x.assignment2.backend.entities.recruitments.Recruitment;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "company", orphanRemoval = true)
    private Set<Recruitment> recruitments = new LinkedHashSet<>();
    
}