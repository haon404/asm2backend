package prj321x.assignment2.backend.entities.users;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @GetMapping
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        try {
            UserDto userDto = userRepository.findByEmail(authentication.getName())
                    .map(userMapper::toDto)
                    .orElseThrow();
            return ResponseEntity.ok(userDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
