package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.dto.UserDTO;
import dev.clima.securityjwt.entity.User;
import dev.clima.securityjwt.repository.UserDAO;
import dev.clima.securityjwt.security.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private UserDAO userDAO;
    private JWTUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userDAO.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return Collections.singletonMap("token", token);
    }

    @PostMapping("/login")
    public Map<String, Object> auth(@RequestBody UserDTO userDTO) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(),
                            userDTO.getPassword());
            authenticationManager.authenticate(authenticationToken);
            String token = jwtUtil.generateToken(userDTO.getEmail());

            return Collections.singletonMap("token", token);
        } catch (AuthenticationException authenticationException) {
            throw new RuntimeException("Invalid credentials");
        }
    }

}
