package prj321x.assignment2.backend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prj321x.assignment2.backend.services.TokenService;

@RestController
@RequestMapping("/api/v1/login")
@AllArgsConstructor
@CrossOrigin
public class LoginController {
    private final TokenService tokenService;
    
    @PostMapping
    public ResponseEntity<String> login(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        return ResponseEntity.ok(token);
    }
}
