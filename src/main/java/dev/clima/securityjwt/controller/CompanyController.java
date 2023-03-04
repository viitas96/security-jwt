package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.dto.CompanyDTO;
import dev.clima.securityjwt.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/companies")
public class CompanyController {

    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable long id) {
        return ResponseEntity.ok(companyService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getCompanies() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> addCompany(@RequestBody CompanyDTO dto) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(location).body(companyService.save(dto));
    }

}
