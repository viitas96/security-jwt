package dev.clima.securityjwt.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    private String message;

    public ResourceNotFoundException(String message) {
        this.message = message;
    }
}
