package com.emresahna.customerservice.service;

import com.emresahna.customerservice.dto.AddBalanceRequest;
import com.emresahna.customerservice.dto.CustomerWalletRequest;
import com.emresahna.customerservice.entity.CustomerWallet;
import com.emresahna.customerservice.repository.CustomerWalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public record CustomerWalletService(CustomerWalletRepository customerWalletRepository) {

    public CustomerWallet createCustomerWallet(CustomerWalletRequest customerWallet) {
        return customerWalletRepository.save(CustomerWallet.builder()
                .customerId(customerWallet.getCustomer_id())
                .balance(BigDecimal.valueOf(0.0))
                .build());
    }

    public CustomerWallet addBalance(AddBalanceRequest addBalanceRequest) {
        CustomerWallet customerWallet = customerWalletRepository.findByCustomerId(addBalanceRequest.getCustomerId());
        customerWallet.setBalance(customerWallet.getBalance().add(addBalanceRequest.getAmount()));
        return customerWalletRepository.save(customerWallet);
    }
}
