package com.emresahna.sellerservice;

import com.emresahna.sellerservice.entity.Seller;
import com.emresahna.sellerservice.repository.SellerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;

@SpringBootApplication
public class SellerServiceApplication {

	private SellerRepository sellerRepository;

	public SellerServiceApplication(SellerRepository sellerRepository) {
		this.sellerRepository = sellerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SellerServiceApplication.class, args);
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
					.bankDetails("1234567890")
					.createdAt(new Timestamp(System.currentTimeMillis()))
					.build();
			sellerRepository.save(seller);
		};
	}
}
