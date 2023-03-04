package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.dto.LoginDTO;
import dev.clima.securityjwt.dto.RegisterUserDTO;
import dev.clima.securityjwt.dto.TokenDTO;
import dev.clima.securityjwt.security.service.SecurityService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private ModelMapper modelMapper;

    private SecurityService securityService;

    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@RequestBody RegisterUserDTO dto) {
        return ResponseEntity.ok(new TokenDTO(securityService.createUser(dto)));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> auth(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(new TokenDTO(securityService.authenticate(loginDTO)));
    }

}
