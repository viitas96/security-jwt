package dev.betisor.securityjwt.security.service;

import dev.betisor.securityjwt.dto.LoginDTO;
import dev.betisor.securityjwt.dto.RegisterUserDTO;
import dev.betisor.securityjwt.dto.TokenDTO;
import dev.betisor.securityjwt.entity.User;
import dev.betisor.securityjwt.security.util.JWTUtil;
import dev.betisor.securityjwt.util.exception.BadCredentialsException;
import dev.betisor.securityjwt.repository.UserDAO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityService {

    private final UserDAO userDAO;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public TokenDTO authenticate(LoginDTO dto) {
        User user = modelMapper.map(dto, User.class);
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getEmail(),
                            user.getPassword());
            authenticationManager.authenticate(authenticationToken);

            var token = jwtUtil.generateToken(user.getEmail());
            return new TokenDTO(token, user.getNickName());
        } catch (AuthenticationException authenticationException) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    public TokenDTO createUser(RegisterUserDTO dto) {
        User user = modelMapper.map(dto, User.class);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userDAO.save(user);

        var token = jwtUtil.generateToken(user.getEmail());
        return new TokenDTO(token, user.getNickName());
    }

}
