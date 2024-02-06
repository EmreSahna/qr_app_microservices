package com.emresahna.walletservice.service.impl;

import com.emresahna.walletservice.dto.BalanceRequest;
import com.emresahna.walletservice.dto.CreateWalletRequest;
import com.emresahna.walletservice.dto.TransactionEvent;
import com.emresahna.walletservice.entity.Wallet;
import com.emresahna.walletservice.exception.WalletNotFoundException;
import com.emresahna.walletservice.repository.WalletRepository;
import com.emresahna.walletservice.service.WalletService;
import com.emresahna.walletservice.util.GenerateQRCode;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    @Override
    public Wallet createWallet(CreateWalletRequest walletRequest) {
        return walletRepository.save(Wallet.builder()
                .userId(walletRequest.getUserId())
                .balance(BigDecimal.valueOf(0.0))
                .build());
    }

    @Override
    public Wallet addBalance(BalanceRequest balanceRequest) {
        Wallet wallet = getWalletByUserId(balanceRequest.getId());

        wallet.setBalance(wallet.getBalance().add(balanceRequest.getAmount()));
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet decrementBalance(BalanceRequest balanceRequest) {
        Wallet wallet = getWalletByUserId(balanceRequest.getId());

        if(wallet.getBalance().compareTo(balanceRequest.getAmount()) < 0) {
            throw new IllegalArgumentException("Balance is not enough");
        }

        wallet.setBalance(wallet.getBalance().subtract(balanceRequest.getAmount()));
        return walletRepository.save(wallet);
    }

    @KafkaListener(topics = "product_availability_success")
    private void doTransaction(TransactionEvent transactionEvent) {
        try {
            decrementBalance(BalanceRequest.builder().id(transactionEvent.getCustomerId()).amount(transactionEvent.getAmount()).build());
            addBalance(BalanceRequest.builder().id(transactionEvent.getSellerId()).amount(transactionEvent.getAmount()).build());
        }catch (Exception e){
            kafkaTemplate.send("payment_failed", transactionEvent);
            return;
        }

        kafkaTemplate.send("payment_success", transactionEvent);
    }

    @Override
    public Wallet getWalletByUserId(String userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(this::throwWalletNotFoundException);
    }

    private WalletNotFoundException throwWalletNotFoundException() {
        throw new WalletNotFoundException();
    }

    @Override
    public byte[] generateQrCode(String userId) {
        return GenerateQRCode.generateQRCode(userId);
    }
}
