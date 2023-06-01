package dev.betisor.securityjwt.controller.exception;

import dev.betisor.securityjwt.util.exception.BadCredentialsException;
import dev.betisor.securityjwt.util.exception.ResourceNotFoundException;
import dev.betisor.securityjwt.util.message.GenericMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    private ResponseEntity<GenericMessage> handleException(BadCredentialsException e) {
        return ResponseEntity.badRequest().body(new GenericMessage(e.getMessage()));
    }

    @ExceptionHandler
    private ResponseEntity<GenericMessage> handleException(ResourceNotFoundException e) {
        return ResponseEntity.badRequest().body(new GenericMessage(e.getMessage()));
    }

    @ExceptionHandler
    private ResponseEntity<GenericMessage> handleException(SQLIntegrityConstraintViolationException e) {
        return ResponseEntity.badRequest().body(new GenericMessage(e.getMessage()));
    }

}
