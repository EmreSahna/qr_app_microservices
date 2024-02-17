package com.quickpayr.walletservice.service.impl;

import com.quickpayr.walletservice.dto.BalanceRequest;
import com.quickpayr.walletservice.dto.CreateWalletRequest;
import com.quickpayr.walletservice.dto.TransactionEvent;
import com.quickpayr.walletservice.entity.Wallet;
import com.quickpayr.walletservice.exception.WalletNotFoundException;
import com.quickpayr.walletservice.repository.WalletRepository;
import com.quickpayr.walletservice.service.WalletService;
import com.quickpayr.walletservice.util.GenerateQRCode;
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
        Wallet wallet = getWalletByUserId(balanceRequest.getUserId());

        wallet.setBalance(wallet.getBalance().add(balanceRequest.getAmount()));
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet decrementBalance(BalanceRequest balanceRequest) {
        Wallet wallet = getWalletByUserId(balanceRequest.getUserId());

        if(wallet.getBalance().compareTo(balanceRequest.getAmount()) < 0) {
            throw new IllegalArgumentException("Balance is not enough");
        }

        wallet.setBalance(wallet.getBalance().subtract(balanceRequest.getAmount()));
        return walletRepository.save(wallet);
    }

    @KafkaListener(id = "product-availability-success-listener", topics = "product_availability_success")
    private void doDecrement(TransactionEvent transactionEvent) {
        try {
            decrementBalance(BalanceRequest.builder().userId(transactionEvent.getCustomerId()).amount(transactionEvent.getAmount()).build());
        }catch (IllegalArgumentException | WalletNotFoundException e){
            kafkaTemplate.send("payment_failed", transactionEvent);
            return;
        }

        kafkaTemplate.send("decrement_success", transactionEvent);
    }

    @KafkaListener(id = "balance_decrement-listener", topics = "decrement_success")
    private void doIncrement(TransactionEvent transactionEvent) {
        try {
            addBalance(BalanceRequest.builder().userId(transactionEvent.getSellerId()).amount(transactionEvent.getAmount()).build());
        }catch (IllegalArgumentException | WalletNotFoundException e){
            kafkaTemplate.send("decrement_rollback", transactionEvent);
            return;
        }

        kafkaTemplate.send("payment_success", transactionEvent);
        kafkaTemplate.send("notify_users", transactionEvent);
    }

    @KafkaListener(id = "balance_rollback-listener",topics = "decrement_rollback")
    private void doIncrementRollback(TransactionEvent transactionEvent) {
        addBalance(BalanceRequest.builder().userId(transactionEvent.getCustomerId()).amount(transactionEvent.getAmount()).build());
        kafkaTemplate.send("payment_failed", transactionEvent);
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
