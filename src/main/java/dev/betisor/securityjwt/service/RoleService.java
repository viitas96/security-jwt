package dev.betisor.securityjwt.service;

import dev.betisor.securityjwt.entity.Role;
import dev.betisor.securityjwt.repository.RoleDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleDAO roleDAO;

    public List<Role> getAll() {
        return roleDAO.findAll();
    }

    public Role getById(Long id) {
        return roleDAO.findById(id).orElseThrow(null);
    }

    public Role save(Role role) {
        return roleDAO.save(role);
    }

    public Role update(Role role, Long id) {
        Role existingRole = roleDAO.findById(id).orElseThrow(null);
        existingRole.setName(role.getName());
        return roleDAO.save(existingRole);
    }

    public void delete(Long id) {
        roleDAO.deleteById(id);
    }
}
