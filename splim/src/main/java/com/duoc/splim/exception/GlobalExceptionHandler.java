package com.duoc.splim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // maneja el error 400 -> una solicitud mal hecha
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> handleValidationErrors(MethodArgumentNotValidException ex) {

        StringBuilder detalle = new StringBuilder();
        for (FieldError campo : ex.getBindingResult().getFieldErrors()) {
            detalle.append(campo.getField())
                   .append(": ")
                   .append(campo.getDefaultMessage())
                   .append(", ");
        }

        ErrorApi error = new ErrorApi(400, "Error de validación", detalle.toString());
        return ResponseEntity.badRequest().body(error);
    }

    // maneja el error 500 -> error del servidor general
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> handleGenericError(Exception ex) {
        ErrorApi error = new ErrorApi(500, "Error interno del servidor", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // maneja el error 404 -> cuando no se encuentra un objeto
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorApi> handleNotFound(NoResourceFoundException ex){
        ErrorApi error = new ErrorApi();
        error.setMensaje(ex.getMessage());
        error.setError("Not Found");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}