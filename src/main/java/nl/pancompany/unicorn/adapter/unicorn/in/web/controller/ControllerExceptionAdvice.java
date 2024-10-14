package nl.pancompany.unicorn.adapter.unicorn.in.web.controller;

import jakarta.validation.ConstraintViolationException;
import nl.pancompany.unicorn.application.unicorn.usecase.exception.UnicornAlreadyExistsException;
import nl.pancompany.unicorn.application.unicorn.usecase.exception.UnicornNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UnicornNotFoundException.class)
    public ResponseEntity<?> handle(UnicornNotFoundException ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(UnicornAlreadyExistsException.class)
    public ResponseEntity<?> handle(UnicornAlreadyExistsException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorMessage("A unicorn with this identifier already exists."));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handle(ConstraintViolationException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new ErrorMessage(ex.toString()));
    }

    record ErrorMessage (String message) {
    }
}
