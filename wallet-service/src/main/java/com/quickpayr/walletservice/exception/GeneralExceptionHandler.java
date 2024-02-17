package com.quickpayr.walletservice.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;
import java.util.List;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(GenericException exception, WebRequest request) {
        var path = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
        return createExceptionResponse(exception, path);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(MethodArgumentNotValidException exception, WebRequest request){
        BindingResult bindingResult = exception.getBindingResult();

        List<String> errors = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        String path = ((ServletWebRequest) request).getRequest().getRequestURL().toString();

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .status(exception.getStatusCode().value())
                .code(HttpStatus.valueOf(exception.getStatusCode().value()).getReasonPhrase())
                .path(path)
                .timestamp(OffsetDateTime.now())
                .detail(errors.toString())
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.valueOf(exception.getStatusCode().value()));
    }

    private ResponseEntity<ExceptionResponse> createExceptionResponse(GenericException exception, String path) {
        var build = ExceptionResponse.builder()
                .status(exception.getStatus())
                .code(exception.getCode())
                .path(path)
                .timestamp(OffsetDateTime.now())
                .detail(exception.getMessage())
                .build();

        return ResponseEntity.status(exception.getStatus()).body(build);
    }
}
