package com.projeto.jwt.exceptions.exceptionHandlers;

import com.projeto.jwt.exceptions.AtividadesNotFoundException;
import com.projeto.jwt.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<StandardError> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        String mensagem = "Status inválido fornecido. Valores permitidos: PENDENTE, CONCLUIDA, CANCELADA.";
        StandardError error =
                new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), mensagem, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatusCode status,
                                                                     WebRequest request) {
        return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
    }
}
