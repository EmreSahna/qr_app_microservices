package com.emresahna.walletservice.service;

import com.emresahna.walletservice.dto.BalanceRequest;
import com.emresahna.walletservice.dto.SellerIdRequest;
import com.emresahna.walletservice.entity.CustomerWallet;
import com.emresahna.walletservice.entity.SellerWallet;
import com.emresahna.walletservice.exception.CustomerWalletNotFoundException;
import com.emresahna.walletservice.exception.SellerWalletNotFoundException;
import com.emresahna.walletservice.repository.SellerWalletRepository;
import com.emresahna.walletservice.util.GenerateQRCode;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public record SellerWalletService(SellerWalletRepository sellerWalletRepository) {

    public SellerWallet createSellerWallet(SellerIdRequest sellerIdRequest) {
        return sellerWalletRepository.save(SellerWallet.builder()
                .sellerId(sellerIdRequest.getSeller_id())
                .balance(BigDecimal.valueOf(0.0))
                .build());
    }

    public SellerWallet withdrawBalance(BalanceRequest balanceRequest) {
        SellerWallet sellerWallet = sellerWalletRepository.findBySellerId(balanceRequest.getId())
                .orElseThrow(() -> new SellerWalletNotFoundException("Customer wallet not found"));
        sellerWallet.setBalance(sellerWallet.getBalance().subtract(balanceRequest.getAmount()));
        return sellerWalletRepository.save(sellerWallet);
    }

    public void addBalance(BalanceRequest balanceRequest) {
        SellerWallet sellerWallet = sellerWalletRepository.findBySellerId(balanceRequest.getId())
                .orElseThrow(() -> new SellerWalletNotFoundException("Customer wallet not found"));
        sellerWallet.setBalance(sellerWallet.getBalance().add(balanceRequest.getAmount()));
        sellerWalletRepository.save(sellerWallet);
    }

    public byte[] generateQrCode(String sellerId) {
        return GenerateQRCode.generateQRCode(sellerId);
    }

    public SellerWallet getSellerWallet(String seller_id) {
        return sellerWalletRepository.findBySellerId(seller_id)
                .orElseThrow(() -> new SellerWalletNotFoundException("Customer wallet not found"));
    }
}
