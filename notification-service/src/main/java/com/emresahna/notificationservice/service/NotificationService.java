package com.emresahna.notificationservice.service;

import com.emresahna.notificationservice.dto.SellerTransactionNotificationRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public NotificationService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @KafkaListener(topics = "seller-transaction")
    public void handleSellerTransaction(SellerTransactionNotificationRequest email) {
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email.getSeller_email());
            helper.setSubject("Transaction Notification");

            Context context = new Context();
            context.setVariable("customer_id", email.getCustomer_id());
            context.setVariable("amount", email.getAmount());
            context.setVariable("createdAt", email.getCreatedAt());
            String htmlBody = templateEngine.process("seller_transaction", context);
            helper.setText(htmlBody, true);

            mailSender.send(message);
            logger.info(String.format("Transaction notification sent to -> %s", email.getSeller_email()));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
