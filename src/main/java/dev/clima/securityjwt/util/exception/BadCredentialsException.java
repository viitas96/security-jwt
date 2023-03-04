package dev.clima.securityjwt.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BadCredentialsException extends RuntimeException{

    private String message;

}
