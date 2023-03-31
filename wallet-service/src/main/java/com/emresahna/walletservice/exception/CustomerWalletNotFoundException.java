package com.emresahna.walletservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerWalletNotFoundException extends RuntimeException {
    public CustomerWalletNotFoundException(String s) {
        super(s);
    }
}
