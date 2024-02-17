package com.quickpayr.walletservice.exception;

public enum ExceptionCodes {

    WALLET_NOT_FOUND("WALLET_NOT_FOUND");

    private final String code;

    ExceptionCodes(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}