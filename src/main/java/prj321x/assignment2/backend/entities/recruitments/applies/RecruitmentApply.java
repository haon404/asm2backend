package prj321x.assignment2.backend.entities.recruitments.applies;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import prj321x.assignment2.backend.entities.recruitments.Recruitment;
import prj321x.assignment2.backend.entities.users.User;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "recruitment_apply")
public class RecruitmentApply {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "recruitment_id", nullable = false)
    private Recruitment recruitment;
    
}