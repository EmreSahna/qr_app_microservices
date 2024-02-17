package com.quickpayr.walletservice.service;

import com.quickpayr.walletservice.dto.BalanceRequest;
import com.quickpayr.walletservice.dto.CreateWalletRequest;
import com.quickpayr.walletservice.entity.Wallet;

public interface WalletService {
    Wallet createWallet(CreateWalletRequest createWalletRequest);
    Wallet addBalance(BalanceRequest balanceRequest);
    Wallet decrementBalance(BalanceRequest balanceRequest);
    Wallet getWalletByUserId(String userId);
    byte[] generateQrCode(String userId);
}
