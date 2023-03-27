package com.emresahna.userservice;

import com.emresahna.userservice.entity.Customer;
import com.emresahna.userservice.entity.Seller;
import com.emresahna.userservice.repository.CustomerRepository;
import com.emresahna.userservice.repository.SellerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;

@SpringBootApplication
public class UserServiceApplication {
	private final CustomerRepository customerRepository;
	private final SellerRepository sellerRepository;

	public UserServiceApplication(CustomerRepository customerRepository, SellerRepository sellerRepository) {
		this.customerRepository = customerRepository;
		this.sellerRepository = sellerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			Seller seller = Seller.builder()
					.sellerName("Emre Sahna")
					.taxId("1234567890")
					.phone("1234567890")
					.email("emre.sahna@hotmail.com")
					.accountNumber("1234567890")
					.password("1234")
					.bankDetails("1234567890")
					.createdAt(new Timestamp(System.currentTimeMillis()))
					.build();
			sellerRepository.save(seller);

			Customer customer = Customer.builder()
					.customerName("Huseyin")
					.email("huseyin@hotmail.com")
					.phone("123456789")
					.password("1234")
					.bankDetails("123456789")
					.createdAt(new Timestamp(System.currentTimeMillis()))
					.build();
			customerRepository.save(customer);
		};
	}
}
