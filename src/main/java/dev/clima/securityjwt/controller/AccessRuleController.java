package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.dto.AccessRuleDTO;
import dev.clima.securityjwt.entity.AccessRule;
import dev.clima.securityjwt.service.AccessRuleService;
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

@RestController
@AllArgsConstructor
@RequestMapping("/api/access-rules")
public class AccessRuleController {

    private AccessRuleService accessRuleService;

    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AccessRuleDTO> getAccessRule(@PathVariable long id) {
        return new ResponseEntity<>(modelMapper.map(accessRuleService.getById(id), AccessRuleDTO.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Status> addAccessRule(@RequestBody AccessRuleDTO accessRuleDTO) {
        accessRuleService.save(modelMapper.map(accessRuleDTO, AccessRule.class));
        return new ResponseEntity<>(new Status("Success"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> addPathToRule(@PathVariable long id, @RequestBody EntityId entityId) {
        accessRuleService.addRuleToPath(id, entityId.getId());
        return new ResponseEntity<>(new Status("Success"), HttpStatus.OK);
    }

}
