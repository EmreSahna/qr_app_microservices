package com.emresahna.customerservice.service;

import com.emresahna.customerservice.dto.BalanceRequest;
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

    public CustomerWallet addBalance(BalanceRequest balanceRequest) {
        CustomerWallet customerWallet = customerWalletRepository.findByCustomerId(balanceRequest.getId());
        customerWallet.setBalance(customerWallet.getBalance().add(balanceRequest.getAmount()));
        return customerWalletRepository.save(customerWallet);
    }

    public CustomerWallet decrementBalance(BalanceRequest balanceRequest) {
        CustomerWallet customerWallet = customerWalletRepository.findByCustomerId(balanceRequest.getId());
        customerWallet.setBalance(customerWallet.getBalance().subtract(balanceRequest.getAmount()));
        return customerWalletRepository.save(customerWallet);
    }

    public CustomerWallet getBalance(String customer_id) {
        return customerWalletRepository.findByCustomerId(customer_id);
    }
}
