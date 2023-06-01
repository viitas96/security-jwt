package dev.betisor.securityjwt.service;

import dev.betisor.securityjwt.dto.PrivilegeDTO;
import dev.betisor.securityjwt.entity.Privilege;
import dev.betisor.securityjwt.entity.Role;
import dev.betisor.securityjwt.repository.PrivilegeDAO;
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

    public Privilege getById(long permissionId) {
        return privilegeDAO.findById(permissionId).orElseThrow(null);
    }
}
