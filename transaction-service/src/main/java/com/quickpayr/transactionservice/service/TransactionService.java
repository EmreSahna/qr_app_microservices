package com.quickpayr.transactionservice.service;

import com.quickpayr.transactionservice.dto.SellerIdResponse;
import com.quickpayr.transactionservice.dto.TransactionRequest;
import com.quickpayr.transactionservice.entity.Transaction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(TransactionRequest transactionRequest);
    List<Transaction> getTransactionsByCustomerId(String id);
    List<Transaction> getTransactionsBySellerId(String id);
    SellerIdResponse getDataFormQRCode(MultipartFile qrPhoto);
}
