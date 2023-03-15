package com.emresahna.sellerservice.service;

import com.emresahna.sellerservice.dto.BalanceRequest;
import com.emresahna.sellerservice.dto.SellerWalletRequest;
import com.emresahna.sellerservice.entity.SellerWallet;
import com.emresahna.sellerservice.repository.SellerWalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public record SellerWalletService(SellerWalletRepository sellerWalletRepository) {

    public SellerWallet createSellerWallet(SellerWalletRequest sellerWalletRequest) {
        return sellerWalletRepository.save(SellerWallet.builder()
                .sellerId(sellerWalletRequest.getSeller_id())
                .balance(BigDecimal.valueOf(0.0))
                .build());
    }

    public SellerWallet withdrawBalance(BalanceRequest balanceRequest) {
        SellerWallet sellerWallet = sellerWalletRepository.findBySellerId(balanceRequest.getId());
        sellerWallet.setBalance(sellerWallet.getBalance().subtract(balanceRequest.getAmount()));
        return sellerWalletRepository.save(sellerWallet);
    }

    public SellerWallet addBalance(BalanceRequest balanceRequest) {
        SellerWallet sellerWallet = sellerWalletRepository.findBySellerId(balanceRequest.getId());
        sellerWallet.setBalance(sellerWallet.getBalance().add(balanceRequest.getAmount()));
        return sellerWalletRepository.save(sellerWallet);
    }
}
