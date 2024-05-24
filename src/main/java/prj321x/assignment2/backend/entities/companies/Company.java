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
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "company_picture")
    private String companyPicture;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @OneToMany(mappedBy = "company", orphanRemoval = true)
    private Set<Recruitment> recruitments = new LinkedHashSet<>();
    
}