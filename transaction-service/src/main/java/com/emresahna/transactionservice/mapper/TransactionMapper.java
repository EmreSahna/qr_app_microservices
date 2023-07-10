package com.emresahna.transactionservice.mapper;

import com.emresahna.transactionservice.dto.TransactionRequest;
import com.emresahna.transactionservice.entity.Transaction;
import com.emresahna.transactionservice.entity.TransactionStatus;

public class TransactionMapper {
    public static Transaction mapTransactionRequestToTransaction(TransactionRequest transactionRequest) {
        return Transaction.builder()
                .customerId(transactionRequest.getCustomerId())
                .sellerId(transactionRequest.getSellerId())
                .productIds(transactionRequest.getPurchasedItems())
                .amount(transactionRequest.getAmount())
                .status(TransactionStatus.PENDING)
                .build();
    }
}
