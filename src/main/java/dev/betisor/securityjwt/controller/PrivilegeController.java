package dev.betisor.securityjwt.controller;

import dev.betisor.securityjwt.dto.PrivilegeDTO;
import dev.betisor.securityjwt.dto.ResponsePrivilegeDTO;
import dev.betisor.securityjwt.service.PrivilegeService;
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
@RequestMapping("/api/privileges")
public class PrivilegeController {

    private final ModelMapper modelMapper;

    private final PrivilegeService privilegeService;

    @GetMapping
    public List<ResponsePrivilegeDTO> getPrivileges() {
        return privilegeService.getAll().stream().map(e -> modelMapper.map(e, ResponsePrivilegeDTO.class)).toList();
    }

    @PostMapping
    public ResponsePrivilegeDTO addPrivilege(@RequestBody PrivilegeDTO privilegeDTO) {
        return modelMapper.map(privilegeService.save(privilegeDTO), ResponsePrivilegeDTO.class);
    }

}
