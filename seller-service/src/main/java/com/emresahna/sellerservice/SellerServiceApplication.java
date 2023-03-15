package com.emresahna.sellerservice;

import com.emresahna.sellerservice.entity.Seller;
import com.emresahna.sellerservice.entity.SellerWallet;
import com.emresahna.sellerservice.repository.SellerRepository;
import com.emresahna.sellerservice.repository.SellerWalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

@SpringBootApplication
public class SellerServiceApplication {

	private SellerRepository sellerRepository;
	private SellerWalletRepository sellerWalletRepository;

	public SellerServiceApplication(SellerRepository sellerRepository, SellerWalletRepository sellerWalletRepository) {
		this.sellerRepository = sellerRepository;
		this.sellerWalletRepository = sellerWalletRepository;
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

			SellerWallet sellerWallet = SellerWallet.builder()
					.sellerId(seller.getId())
					.balance(BigDecimal.valueOf(0.0))
					.build();
			sellerWalletRepository.save(sellerWallet);
		};
	}
}
