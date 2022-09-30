package dev.clima.securityjwt.service;

import dev.clima.securityjwt.entity.Path;
import dev.clima.securityjwt.entity.Privilege;
import dev.clima.securityjwt.entity.Role;
import dev.clima.securityjwt.repository.PathDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PathService {

    private PathDAO pathDAO;

    private RoleService roleService;

    private PrivilegeService privilegeService;

    public Path addPath(Path path) {
        return pathDAO.save(path);
    }

    public List<Path> getAll() {
        return pathDAO.findAll();
    }

    public Path getPath(long id) {
        return pathDAO.findById(id).orElseThrow(null);
    }

    public void addRole(long pathId, long roleId) {
        Path path = getPath(pathId);
        Role role = roleService.getById(roleId);
        path.getRoles().add(role);
        role.getPaths().add(path);
        pathDAO.save(path);
    }

    public void addPermission(long pathId, long permissionId) {
        Path path = getPath(pathId);
        Privilege privilege = privilegeService.getById(permissionId);
        path.getPrivileges().add(privilege);
        privilege.getPaths().add(path);
        pathDAO.save(path);

    }

}
