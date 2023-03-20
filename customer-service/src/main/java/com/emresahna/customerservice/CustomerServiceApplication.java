package com.emresahna.customerservice;

import com.emresahna.customerservice.entity.Customer;
import com.emresahna.customerservice.entity.CustomerWallet;
import com.emresahna.customerservice.repository.CustomerRepository;
import com.emresahna.customerservice.repository.CustomerWalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

@SpringBootApplication
@EnableFeignClients
public class CustomerServiceApplication {
	private final CustomerRepository customerRepository;
	private final CustomerWalletRepository customerWalletRepository;

	public CustomerServiceApplication(CustomerRepository customerRepository, CustomerWalletRepository customerWalletRepository) {
		this.customerRepository = customerRepository;
		this.customerWalletRepository = customerWalletRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			Customer customer = Customer.builder()
					.customerName("Huseyin")
					.email("huseyin@hotmail.com")
					.phone("123456789")
					.password("1234")
					.bankDetails("123456789")
					.createdAt(new Timestamp(System.currentTimeMillis()))
					.build();
			customerRepository.save(customer);

			CustomerWallet customerWallet = CustomerWallet.builder()
					.customerId(customer.getId())
					.balance(BigDecimal.valueOf(0.0))
					.build();
			customerWalletRepository.save(customerWallet);
		};
	}
}
