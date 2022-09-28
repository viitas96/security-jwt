package dev.clima.securityjwt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RegisterUserDTO {

    private String email;

    private String password;

    @JsonProperty("roles_ids")
    private List<Long> rolesIds;
}
