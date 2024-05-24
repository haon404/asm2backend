package prj321x.assignment2.backend.entities.users;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import prj321x.assignment2.backend.services.FileService;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final FileService fileService;
    
    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('APPLICANT', 'RECRUITER')")
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
    
    @PostMapping("/user/{id}/picture")
    @PreAuthorize("hasAnyAuthority('APPLICANT', 'RECRUITER')")
    public ResponseEntity<String> saveProfilePicture(@RequestParam("file") MultipartFile file,
                                                     @PathVariable("id") String id) {
//        Save multipart file to local storage using FileService
        try {
            String fileLocation = fileService.saveFile(file);
            User user = userRepository.findById(UUID.fromString(id))
                    .orElseThrow();
            if (user.getProfilePicture() != null) {
                fileService.deleteFile(user.getProfilePicture());
            }
            user.setProfilePicture(fileLocation);
            userRepository.save(user);
            return ResponseEntity.ok(fileLocation);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/user/{id}/picture")
    @PreAuthorize("hasAnyAuthority('APPLICANT', 'RECRUITER')")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable("id") String id) {
        try {
            User user = userRepository.findById(UUID.fromString(id))
                    .orElseThrow();
            byte[] profilePicture = fileService.getFile(user.getProfilePicture());
            return ResponseEntity.ok(profilePicture);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/user/update")
    @PreAuthorize("hasAnyAuthority('APPLICANT', 'RECRUITER')")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        try {
            User user = userRepository.findById(userDto.id())
                    .orElseThrow();
            user = userMapper.partialUpdate(userDto, user);
            userRepository.save(user);
            return ResponseEntity.ok(userMapper.toDto(user));
        } catch (NoSuchElementException e) {
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
