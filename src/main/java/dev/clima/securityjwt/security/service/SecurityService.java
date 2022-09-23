package dev.clima.securityjwt.security.service;

import dev.clima.securityjwt.entity.User;
import dev.clima.securityjwt.repository.UserDAO;
import dev.clima.securityjwt.security.util.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityService {

    private UserDAO userDAO;
    private JWTUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    public String authenticate(User user) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getEmail(),
                            user.getPassword());
            authenticationManager.authenticate(authenticationToken);

            return jwtUtil.generateToken(user.getEmail());
        } catch (AuthenticationException authenticationException) {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public String createUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userDAO.save(user);

        return jwtUtil.generateToken(user.getEmail());
    }

}
