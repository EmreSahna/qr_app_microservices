package com.emresahna.transactionservice.service.impl;

import com.emresahna.transactionservice.client.ProductService;
import com.emresahna.transactionservice.client.UserService;
import com.emresahna.transactionservice.client.WalletService;
import com.emresahna.transactionservice.dto.*;
import com.emresahna.transactionservice.entity.Transaction;
import com.emresahna.transactionservice.entity.TransactionStatus;
import com.emresahna.transactionservice.mapper.TransactionMapper;
import com.emresahna.transactionservice.repository.TransactionRepository;
import com.emresahna.transactionservice.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletService walletService;
    private final KafkaTemplate<String, SellerTransactionNotificationRequest> kafkaTemplate;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public Transaction createTransaction(TransactionRequest transactionRequest) {
        productService.checkProductsAvabilityAndPrice(transactionRequest.getPurchasedItems().toArray(new ProductTransactionRequest[0]));

        Transaction transaction = TransactionMapper.mapTransactionRequestToTransaction(transactionRequest);
        transactionRepository.save(transaction);

        decrementBalanceFromCustomerWallet(transaction);

        addPaymentToSellerWallet(transaction);

        notifySellerWithEmail(transaction);

        changeTransactionStatus(transaction.getId(), TransactionStatus.COMPLETED);

        return transaction;
    }

    public void decrementBalanceFromCustomerWallet(Transaction transaction){
        walletService.decrementBalance(BalanceRequest.builder()
                .id(transaction.getCustomerId())
                .amount(transaction.getAmount())
                .build());
    }

    public void addPaymentToSellerWallet(Transaction transaction){
        walletService.addBalance(BalanceRequest.builder()
                .id(transaction.getSellerId())
                .amount(transaction.getAmount())
                .build());
    }

    public void changeTransactionStatus(String transactionId, TransactionStatus status){
        Transaction transaction = transactionRepository.findById(transactionId).get();
        transaction.setStatus(status);
        transactionRepository.save(transaction);
    }

    public void notifySellerWithEmail(Transaction transaction){
        kafkaTemplate.send("seller-transaction", new SellerTransactionNotificationRequest(
                transaction.getCustomerId(),
                userService.getSellerEmail(transaction.getSellerId()),
                transaction.getAmount(),
                transaction.getCreatedAt()
        ));
    }

    @Override
    public List<Transaction> getTransactionsByCustomerId(String id) {
        return transactionRepository.findAllByCustomerId(id);
    }

    @Override
    public List<Transaction> getTransactionsBySellerId(String id) {
        return transactionRepository.findAllBySellerId(id);
    }

    @Override
    public SellerIdResponse getDataFormQRCode(MultipartFile qrPhoto) {
        BinaryBitmap binaryBitmap = null;
        try {
            binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(qrPhoto.getInputStream()))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Result res = null;
        try {
            res = new MultiFormatReader().decode(binaryBitmap);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        SellerIdResponse sellerIdResponse = null;
        try {
            sellerIdResponse = objectMapper.readValue(res.getText(), SellerIdResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return sellerIdResponse;
    }
}
