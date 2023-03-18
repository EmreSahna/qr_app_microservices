package com.emresahna.customerservice.service;

import com.emresahna.customerservice.client.SellerTransactionService;
import com.emresahna.customerservice.dto.BalanceRequest;
import com.emresahna.customerservice.dto.CustomerTransactionRequest;
import com.emresahna.customerservice.dto.SellerIdResponse;
import com.emresahna.customerservice.dto.TransactionRequest;
import com.emresahna.customerservice.entity.CustomerTransaction;
import com.emresahna.customerservice.repository.CustomerTransactionRepository;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;

@Service
public record CustomerTransactionService(CustomerTransactionRepository customerTransactionRepository, CustomerWalletService customerWalletService, SellerTransactionService sellerTransactionService) {

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

        Boolean isTransactionDone = sellerTransactionService.createSellerTransactionWithWallet(
                TransactionRequest.builder()
                        .seller_id(customerTransaction.getSellerId())
                        .buyer_id(customerTransaction.getCustomerId())
                        .purchased_item_id(customerTransaction.getPurchasedItemId())
                        .amount(customerTransaction.getAmount())
                        .build()).getBody();

        if (isTransactionDone) {
            changeTransactionStatus(customerTransaction.getId(), "SUCCESS");

            customerWalletService.decrementBalance(BalanceRequest.builder()
                        .id(customerTransaction.getCustomerId())
                        .amount(customerTransaction.getAmount())
                    .build());

            return "Transaction is successful";
        }

        changeTransactionStatus(customerTransaction.getId(), "FAILED");
        return "Transaction is failed";
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
}
