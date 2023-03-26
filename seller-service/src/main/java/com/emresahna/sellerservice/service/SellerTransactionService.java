package com.emresahna.sellerservice.service;

import com.emresahna.sellerservice.dto.BalanceRequest;
import com.emresahna.sellerservice.dto.SellerTransactionRequest;
import com.emresahna.sellerservice.dto.TransactionRequest;
import com.emresahna.sellerservice.entity.SellerTransaction;
import com.emresahna.sellerservice.repository.SellerTransactionRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public record SellerTransactionService(SellerTransactionRepository sellerTransactionRepository, SellerWalletService sellerWalletService) {

    public SellerTransaction createSellerTransaction(SellerTransactionRequest sellerTransactionRequest) {
        return sellerTransactionRepository.save(SellerTransaction.builder()
                .buyerId(sellerTransactionRequest.getBuyer_id())
                .purchaseItemId(sellerTransactionRequest.getPurchased_item_id())
                .amount(sellerTransactionRequest.getAmount())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build());
    }

    public Boolean createSellerTransactionWithWallet(TransactionRequest transactionRequest) {
        sellerWalletService.addBalance(BalanceRequest.builder()
                .id(transactionRequest.getSeller_id())
                .amount(transactionRequest.getAmount())
                .build());

        createSellerTransaction(SellerTransactionRequest.builder()
                .buyer_id(transactionRequest.getBuyer_id())
                .seller_id(transactionRequest.getSeller_id())
                .purchased_item_id(transactionRequest.getPurchased_item_id())
                .amount(transactionRequest.getAmount())
                .build());
        return true;
    }

    public List<SellerTransaction> getTransactions(String sellerId) {
        return sellerTransactionRepository.findBySellerId(sellerId);
    }
}
