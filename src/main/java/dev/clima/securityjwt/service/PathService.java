package dev.clima.securityjwt.service;

import dev.clima.securityjwt.entity.Path;
import dev.clima.securityjwt.entity.Role;
import dev.clima.securityjwt.entity.Privilege;
import dev.clima.securityjwt.repository.PathDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Service
@AllArgsConstructor
public class PathService {

    private PathDAO pathDAO;

    private RoleService roleService;

    private PrivilegeService privilegeService;

    public void addPath(Path path) {

        if (path.getHttpMethod() == null) {
            String name = path.getName();
            List<Path> paths = List.of(
                    Path.builder().httpMethod(GET).name(name).build(),
                    Path.builder().httpMethod(POST).name(name).build(),
                    Path.builder().httpMethod(PUT).name(name).build(),
                    Path.builder().httpMethod(PATCH).name(name).build(),
                    Path.builder().httpMethod(DELETE).name(name).build()
            );
            pathDAO.saveAll(paths);
        } else {
            pathDAO.save(path);
        }

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
