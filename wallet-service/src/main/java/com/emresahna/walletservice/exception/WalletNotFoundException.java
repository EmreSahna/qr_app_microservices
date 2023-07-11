package com.emresahna.walletservice.exception;

public class WalletNotFoundException extends GenericException {
    public WalletNotFoundException() {
        super(ExceptionCodes.WALLET_NOT_FOUND.getCode(), ExceptionCodes.WALLET_NOT_FOUND.getCode(), 404);
    }
}
