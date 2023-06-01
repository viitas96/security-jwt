package dev.betisor.securityjwt.controller;

import dev.betisor.securityjwt.repository.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/manager")
public class ManagerController {

    private UserDAO userDAO;

    @GetMapping("/info")
    public String getUserDetails() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
