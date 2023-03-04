package dev.clima.securityjwt.controller.exception;

import dev.clima.securityjwt.util.exception.ApplicationException;
import dev.clima.securityjwt.util.message.GenericMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<GenericMessage> handleException(ApplicationException e) {
        return ResponseEntity.badRequest().body(new GenericMessage(e.getMessage()));
    }

}
