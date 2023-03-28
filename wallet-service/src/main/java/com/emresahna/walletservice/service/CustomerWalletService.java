package com.emresahna.walletservice.service;

import com.emresahna.walletservice.dto.BalanceRequest;
import com.emresahna.walletservice.dto.CustomerWalletRequest;
import com.emresahna.walletservice.entity.CustomerWallet;
import com.emresahna.walletservice.repository.CustomerWalletRepository;
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

    public Boolean decrementBalance(BalanceRequest balanceRequest) {
        CustomerWallet customerWallet = customerWalletRepository.findByCustomerId(balanceRequest.getId());

        if(customerWallet.getBalance().compareTo(balanceRequest.getAmount()) < 0) {
            return false;
        }

        customerWallet.setBalance(customerWallet.getBalance().subtract(balanceRequest.getAmount()));
        customerWalletRepository.save(customerWallet);
        return true;
    }

    public CustomerWallet getBalance(String customer_id) {
        return customerWalletRepository.findByCustomerId(customer_id);
    }
}
