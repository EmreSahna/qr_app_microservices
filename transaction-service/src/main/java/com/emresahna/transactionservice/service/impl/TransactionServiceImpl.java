package com.emresahna.transactionservice.service.impl;

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
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final KafkaTemplate<String, TransactionEvent> kafkaTemplate;

    @Override
    public Transaction createTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = TransactionMapper.mapTransactionRequestToTransaction(transactionRequest);
        Transaction createdTransaction = transactionRepository.save(transaction);

        kafkaTemplate.send("transaction_created",
                TransactionEvent.builder()
                .id(createdTransaction.getId())
                .customerId(transaction.getCustomerId())
                .sellerId(transaction.getSellerId())
                .productIds(transaction.getProductIds())
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .createdAt(transaction.getCreatedAt())
                .lastModifiedAt(transaction.getLastModifiedAt())
                .build()
        );

        return transaction;
    }

    @KafkaListener(topics = "product_availability_failed", groupId = "transaction-group")
    private void failedProductAvailabilityCheck(TransactionEvent transactionEvent){
        Transaction transaction = transactionRepository.findById(
                transactionEvent.getId()
        ).orElseThrow();

        transaction.setStatus(TransactionStatus.FAILED_PRODUCT_AVAILABILITY);
        transactionRepository.save(transaction);
    }

    @KafkaListener(topics = "product_rollback", groupId = "transaction-group")
    private void failedPayment(TransactionEvent transactionEvent){
        Transaction transaction = transactionRepository.findById(
                transactionEvent.getId()
        ).orElseThrow();

        transaction.setStatus(TransactionStatus.FAILED_PAYMENT);
        transactionRepository.save(transaction);
    }

    @KafkaListener(topics = "payment_success", groupId = "transaction-group")
    private void successPayment(TransactionEvent transactionEvent){
        Transaction transaction = transactionRepository.findById(
                transactionEvent.getId()
        ).orElseThrow();

        transaction.setStatus(TransactionStatus.COMPLETED);
        transactionRepository.save(transaction);
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
