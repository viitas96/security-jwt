package dev.clima.securityjwt.service;

import dev.clima.securityjwt.dto.PrivilegeDTO;
import dev.clima.securityjwt.entity.Privilege;
import dev.clima.securityjwt.entity.Role;
import dev.clima.securityjwt.repository.PrivilegeDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PrivilegeService {

    private final RoleService roleService;

    private final PrivilegeDAO privilegeDAO;

    public Privilege save(PrivilegeDTO privilegeDTO) {
        Role role =  roleService.getById(privilegeDTO.getRoleId());
        Privilege privilege = new Privilege(privilegeDTO.getName());
        privilege.getRoles().add(role);
        role.getPrivileges().add(privilege);
        return privilegeDAO.save(privilege);
    }

    public List<Privilege> getAll() {
        return privilegeDAO.findAll();
    }
}
