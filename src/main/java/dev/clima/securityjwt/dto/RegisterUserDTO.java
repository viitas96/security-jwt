package dev.clima.securityjwt.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserDTO {

    private String email;
    private String password;
    private String nickName;
}
