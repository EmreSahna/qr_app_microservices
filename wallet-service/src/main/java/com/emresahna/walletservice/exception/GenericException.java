package com.emresahna.walletservice.exception;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {
    private final String code;
    private final String message;
    private final Integer status;

    public GenericException(String code, String message, Integer status) {
        super(message);
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
