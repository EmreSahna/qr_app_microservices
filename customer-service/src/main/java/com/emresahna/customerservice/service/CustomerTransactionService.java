package com.emresahna.customerservice.service;

import com.emresahna.customerservice.client.SellerTransactionService;
import com.emresahna.customerservice.dto.BalanceRequest;
import com.emresahna.customerservice.dto.CustomerTransactionRequest;
import com.emresahna.customerservice.dto.TransactionRequest;
import com.emresahna.customerservice.entity.CustomerTransaction;
import com.emresahna.customerservice.repository.CustomerTransactionRepository;
import org.springframework.stereotype.Service;

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
}
