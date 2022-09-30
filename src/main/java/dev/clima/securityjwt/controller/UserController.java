package dev.clima.securityjwt.controller;

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
@RequestMapping("/api/users")
public class UserController {

    private UserDAO userDAO;

    @GetMapping("/info")
    public String getUserDetails() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
