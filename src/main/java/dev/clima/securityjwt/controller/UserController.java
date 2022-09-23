package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.entity.User;
import dev.clima.securityjwt.repository.UserDAO;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@ToString
@RequestMapping("/api/user")
public class UserController {

    private UserDAO userDAO;

    @GetMapping("/info")
    public User getUserDetails() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDAO.findByEmail(email).get();
    }

}