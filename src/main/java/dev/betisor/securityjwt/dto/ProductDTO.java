package dev.betisor.securityjwt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private Float price;

}
