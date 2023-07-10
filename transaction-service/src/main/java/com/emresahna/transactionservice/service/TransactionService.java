package com.emresahna.transactionservice.service;

import com.emresahna.transactionservice.dto.SellerIdResponse;
import com.emresahna.transactionservice.dto.TransactionRequest;
import com.emresahna.transactionservice.entity.Transaction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(TransactionRequest transactionRequest);
    List<Transaction> getTransactionsByCustomerId(String id);
    List<Transaction> getTransactionsBySellerId(String id);
    SellerIdResponse getDataFormQRCode(MultipartFile qrPhoto);
}
