package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.service.CompanyService;
import dev.clima.securityjwt.util.EntityId;
import dev.clima.securityjwt.util.Status;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/company-management/{companyId}")
public class CompanyManagementController {

    private CompanyService companyService;

    @PostMapping("/add-employee")
    public ResponseEntity<Status> addEmployee(@PathVariable long companyId, @RequestBody EntityId entityId) {
        companyService.addUser(companyId, entityId.getId());
        return new ResponseEntity<>(new Status("success"), HttpStatus.CREATED);
    }

}
