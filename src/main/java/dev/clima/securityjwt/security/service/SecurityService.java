package dev.clima.securityjwt.security.service;

import dev.clima.securityjwt.dto.LoginDTO;
import dev.clima.securityjwt.dto.RegisterUserDTO;
import dev.clima.securityjwt.entity.Role;
import dev.clima.securityjwt.entity.User;
import dev.clima.securityjwt.repository.UserDAO;
import dev.clima.securityjwt.security.util.JWTUtil;
import dev.clima.securityjwt.service.RoleService;
import dev.clima.securityjwt.util.exception.BadCredentialsException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    private final UserDAO userDAO;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    private final ModelMapper modelMapper;

    public String authenticate(LoginDTO dto) {
        User user = modelMapper.map(dto, User.class);
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getEmail(),
                            user.getPassword());
            authenticationManager.authenticate(authenticationToken);

            return jwtUtil.generateToken(user.getEmail());
        } catch (AuthenticationException authenticationException) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    public String createUser(RegisterUserDTO dto) {
        User user = modelMapper.map(dto, User.class);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        List<Role> roles = new ArrayList<>();
        dto.getRolesIds().forEach(e -> roles.add(roleService.getById(e)));
        user.setRoles(roles);
        user = userDAO.save(user);

        return jwtUtil.generateToken(user.getEmail());
    }

}
