package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.dto.AccessRuleDTO;
import dev.clima.securityjwt.service.AccessRuleService;
import dev.clima.securityjwt.util.EntityId;
import dev.clima.securityjwt.util.Status;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/access-rules")
public class AccessRuleController {

    private AccessRuleService accessRuleService;

    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AccessRuleDTO> getAccessRule(@PathVariable long id) {
        return ResponseEntity.ok(accessRuleService.getById(id));
    }

    @PostMapping
    public ResponseEntity<AccessRuleDTO> addAccessRule(@RequestBody AccessRuleDTO accessRuleDTO) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(accessRuleDTO.getId())
                .toUri();
        return ResponseEntity.created(location).body(accessRuleService.save(accessRuleDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> addPathToRule(@PathVariable long id, @RequestBody EntityId entityId) {
        accessRuleService.addRuleToPath(id, entityId.getId());
        return ResponseEntity.ok(new Status("Successful"));
    }

}
