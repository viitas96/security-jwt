package dev.clima.securityjwt.dto;

import dev.clima.securityjwt.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {

    private String email;

    private String password;

    private Role role;

}
