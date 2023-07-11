package com.emresahna.walletservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(GenericException exception, WebRequest request) {
        var path = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
        return createExceptionResponse(exception, path);
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
