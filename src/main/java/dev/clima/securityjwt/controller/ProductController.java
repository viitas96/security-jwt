package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.dto.ProductDTO;
import dev.clima.securityjwt.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final ModelMapper modelMapper;

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductDTO>> getCompanies() {
        var response = productService.findAll()
                .stream()
                .map(e -> modelMapper.map(e, ProductDTO.class))
                .toList();
        return ResponseEntity.ok(response);
    }

}
