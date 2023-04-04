package com.emresahna.walletservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SellerWalletNotFoundException extends RuntimeException {
    public SellerWalletNotFoundException(String s) {
        super(s);
    }
}
