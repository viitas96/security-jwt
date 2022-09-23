package dev.clima.securityjwt.security.service;

import dev.clima.securityjwt.entity.User;
import dev.clima.securityjwt.repository.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User not found with username %s", username))
        );

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                Collections.singleton(
                        new SimpleGrantedAuthority("ROLE_USER")
                )
        );
    }
}
