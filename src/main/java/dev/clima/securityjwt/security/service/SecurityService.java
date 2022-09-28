package dev.clima.securityjwt.security.service;

import dev.clima.securityjwt.entity.Role;
import dev.clima.securityjwt.entity.User;
import dev.clima.securityjwt.repository.UserDAO;
import dev.clima.securityjwt.security.util.JWTUtil;
import dev.clima.securityjwt.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SecurityService {

    private UserDAO userDAO;
    private JWTUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    private RoleService roleService;

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

    public String createUser(User user, List<Long> rolesIds) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        List<Role> roles = new ArrayList<>();
        rolesIds.forEach(e -> roles.add(roleService.getById(e)));
        user.setRoles(roles);
        user = userDAO.save(user);

        return jwtUtil.generateToken(user.getEmail());
    }

}
