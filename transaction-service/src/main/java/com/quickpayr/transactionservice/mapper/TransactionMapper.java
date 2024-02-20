package com.quickpayr.transactionservice.mapper;

import com.quickpayr.transactionservice.dto.TransactionRequest;
import com.quickpayr.transactionservice.entity.Transaction;
import com.quickpayr.transactionservice.entity.TransactionStatus;

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
