package com.emresahna.walletservice.service;

import com.emresahna.walletservice.dto.BalanceRequest;
import com.emresahna.walletservice.dto.CreateWalletRequest;
import com.emresahna.walletservice.entity.Wallet;

public interface WalletService {
    Wallet createWallet(CreateWalletRequest createWalletRequest);
    Wallet addBalance(BalanceRequest balanceRequest);
    Wallet decrementBalance(BalanceRequest balanceRequest);
    Wallet getWalletByUserId(String userId);
    byte[] generateQrCode(String userId);
}
