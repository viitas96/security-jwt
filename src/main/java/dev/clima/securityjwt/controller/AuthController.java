package dev.clima.securityjwt.controller;

import dev.clima.securityjwt.dto.RegisterUserDTO;
import dev.clima.securityjwt.dto.TokenDTO;
import dev.clima.securityjwt.dto.LoginDTO;
import dev.clima.securityjwt.entity.User;
import dev.clima.securityjwt.security.service.SecurityService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private ModelMapper modelMapper;

    private SecurityService securityService;

    @PostMapping("/register")
    public TokenDTO register(@RequestBody RegisterUserDTO dto) {
        User user = modelMapper.map(dto, User.class);
        String token = securityService.createUser(user, dto.getRolesIds());

        return new TokenDTO(token);
    }

    @PostMapping("/login")
    public TokenDTO auth(@RequestBody LoginDTO loginDTO) {
        String token = securityService.authenticate(modelMapper.map(loginDTO, User.class));

        return new TokenDTO(token);
    }

}
