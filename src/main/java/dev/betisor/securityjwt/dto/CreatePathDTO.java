package dev.betisor.securityjwt.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import javax.persistence.Enumerated;

@Getter
@Setter
public class CreatePathDTO {

    private String name;

    @Enumerated
    private HttpMethod httpMethod;

}
