package com.projeto.jwt.exceptions.exceptionHandlers;

import com.projeto.jwt.exceptions.AtividadesNotFoundException;
import com.projeto.jwt.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AtividadesNotFoundException.class)
    public ResponseEntity<StandardError> handleAtividadeNotFoundException(AtividadesNotFoundException ex , HttpServletRequest request){

        StandardError error =
                new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatusCode status,
                                                                     WebRequest request) {
        return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
    }
}
