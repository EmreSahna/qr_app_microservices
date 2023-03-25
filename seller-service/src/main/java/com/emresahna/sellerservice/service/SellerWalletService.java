package com.emresahna.sellerservice.service;

import com.emresahna.sellerservice.dto.BalanceRequest;
import com.emresahna.sellerservice.dto.SellerIdRequest;
import com.emresahna.sellerservice.entity.SellerWallet;
import com.emresahna.sellerservice.repository.SellerWalletRepository;
import com.emresahna.sellerservice.util.GenerateQRCode;
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
        SellerWallet sellerWallet = sellerWalletRepository.findBySellerId(balanceRequest.getId());
        sellerWallet.setBalance(sellerWallet.getBalance().subtract(balanceRequest.getAmount()));
        return sellerWalletRepository.save(sellerWallet);
    }

    public SellerWallet addBalance(BalanceRequest balanceRequest) {
        SellerWallet sellerWallet = sellerWalletRepository.findBySellerId(balanceRequest.getId());
        sellerWallet.setBalance(sellerWallet.getBalance().add(balanceRequest.getAmount()));
        return sellerWalletRepository.save(sellerWallet);
    }

    public byte[] generateQrCode(SellerIdRequest sellerIdRequest) {
        return GenerateQRCode.generateQRCode(sellerIdRequest);
    }
}
