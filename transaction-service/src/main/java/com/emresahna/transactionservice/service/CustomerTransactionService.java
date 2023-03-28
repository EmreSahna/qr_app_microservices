package com.emresahna.transactionservice.service;

import com.emresahna.transactionservice.client.WalletService;
import com.emresahna.transactionservice.dto.BalanceRequest;
import com.emresahna.transactionservice.dto.CustomerTransactionRequest;
import com.emresahna.transactionservice.dto.SellerIdResponse;
import com.emresahna.transactionservice.dto.TransactionRequest;
import com.emresahna.transactionservice.entity.CustomerTransaction;
import com.emresahna.transactionservice.repository.CustomerTransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;
import java.sql.Timestamp;

@Service
public record CustomerTransactionService(CustomerTransactionRepository customerTransactionRepository, WalletService walletService, SellerTransactionService sellerTransactionService) {

    public CustomerTransaction createCustomerTransaction(CustomerTransactionRequest customerTransactionRequest) {
        return customerTransactionRepository.save(CustomerTransaction.builder()
                .customerId(customerTransactionRequest.getCustomer_id())
                .sellerId(customerTransactionRequest.getSeller_id())
                .purchasedItemId(customerTransactionRequest.getPurchased_item_id())
                .status("PENDING")
                .amount(customerTransactionRequest.getAmount())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build());
    }

    public void changeTransactionStatus(String transactionId,String status){
        customerTransactionRepository.findById(transactionId).ifPresent(customerTransaction -> {
            customerTransaction.setStatus(status);
            customerTransactionRepository.save(customerTransaction);
        });
    }

    public String createCustomerTransactionWithWallet(CustomerTransactionRequest customerTransactionRequest) {
        CustomerTransaction customerTransaction = createCustomerTransaction(customerTransactionRequest);

        if(!walletService.decrementBalance(BalanceRequest.builder()
                .id(customerTransaction.getCustomerId())
                .amount(customerTransaction.getAmount())
                .build())){
            changeTransactionStatus(customerTransaction.getId(), "CANCELLED");
            return "Transaction is failed";
        }

        if(!sellerTransactionService.createSellerTransactionWithWallet(
                TransactionRequest.builder()
                        .seller_id(customerTransaction.getSellerId())
                        .buyer_id(customerTransaction.getCustomerId())
                        .purchased_item_id(customerTransaction.getPurchasedItemId())
                        .amount(customerTransaction.getAmount())
                        .build())){
            changeTransactionStatus(customerTransaction.getId(), "FAILED");
            return "Transaction is failed";
        }

        changeTransactionStatus(customerTransaction.getId(), "SUCCESS");
        return "Transaction is success";
    }

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

    public List<CustomerTransaction> getTransactions(String customerId) {
        return customerTransactionRepository.findAllByCustomerId(customerId);
    }
}
