package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.dto.RoleDTO;
import dev.clima.securityjwt.entity.Role;
import dev.clima.securityjwt.service.RoleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/roles")
public class RoleController {

    private ModelMapper modelMapper;

    private final RoleService roleService;

    @GetMapping
    public List<Role> getRoles() {
        return roleService.getAll();
    }

    @PostMapping
    public Role addRole(@RequestBody RoleDTO roleDTO) {
        Role role = modelMapper.map(roleDTO, Role.class);
        return roleService.save(role);
    }

}

