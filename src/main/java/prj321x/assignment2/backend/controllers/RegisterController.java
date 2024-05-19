package prj321x.assignment2.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import prj321x.assignment2.backend.entities.roles.RoleRepository;
import prj321x.assignment2.backend.entities.users.RegisterMapper;
import prj321x.assignment2.backend.entities.users.User;
import prj321x.assignment2.backend.entities.users.UserRepository;

@RestController
@RequestMapping("/api/v1/register")
@AllArgsConstructor
@CrossOrigin
public class RegisterController {
    
    private final UserRepository userRepository;
    private final RegisterMapper registerMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        try {
            User user = registerMapper.toEntity(registerDto);
            user.setRole(roleRepository
                    .findByRoleName(registerDto.roleRoleName())
                    .orElseThrow(() -> new RuntimeException("Role not found")));
            user.setPassword(passwordEncoder.encode(registerDto.password()));
            userRepository.save(user);
            return ResponseEntity.ok("Registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
