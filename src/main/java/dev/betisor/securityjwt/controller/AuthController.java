package dev.betisor.securityjwt.controller;

import dev.betisor.securityjwt.dto.LoginDTO;
import dev.betisor.securityjwt.dto.RegisterUserDTO;
import dev.betisor.securityjwt.dto.TokenDTO;
import dev.betisor.securityjwt.security.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private SecurityService securityService;

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@RequestBody RegisterUserDTO dto) {
        var response = securityService.createUser(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> auth(@RequestBody LoginDTO loginDTO) {
        var response = securityService.authenticate(loginDTO);
        return ResponseEntity.ok(response);
    }

}
