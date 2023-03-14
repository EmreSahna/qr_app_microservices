package com.emresahna.sellerservice;

import com.emresahna.sellerservice.entity.Seller;
import com.emresahna.sellerservice.repository.SellerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
					.seller_name("Emre Sahna")
					.tax_id("1234567890")
					.phone("1234567890")
					.email("emre.sahna@hotmail.com")
					.account_number("1234567890")
					.bank_details("1234567890")
					.build();
			sellerRepository.save(seller);
		};
	}
}
