package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.dto.CreatePathDTO;
import dev.clima.securityjwt.entity.Path;
import dev.clima.securityjwt.service.PathService;
import dev.clima.securityjwt.util.EntityId;
import dev.clima.securityjwt.util.Status;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/paths")
public class PathController {

    private PathService pathService;

    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<CreatePathDTO>> getPaths() {
        return new ResponseEntity<>(pathService.getAll().stream()
                .map(e -> modelMapper.map(e, CreatePathDTO.class)).toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Status> addPath(@RequestBody CreatePathDTO dto) {
        pathService.addPath(modelMapper.map(dto, Path.class));
        return new ResponseEntity<>(new Status("success"), HttpStatus.CREATED);
    }

    @PutMapping("/add-role/{pathId}")
    public ResponseEntity<Status> addRoleToPath(@PathVariable long pathId, @RequestBody EntityId entityId) {
        pathService.addRole(pathId, entityId.getId());
        return new ResponseEntity<>(new Status("Success"), HttpStatus.OK);
    }

    @PutMapping("/add-permission/{pathId}")
    public ResponseEntity<Status> addPermissionToPath(@PathVariable long pathId, @RequestBody EntityId entityId) {
        pathService.addPermission(pathId, entityId.getId());
        return new ResponseEntity<>(new Status("Success"), HttpStatus.OK);
    }

}
