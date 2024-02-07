package com.emresahna.notificationservice.service;

import com.emresahna.notificationservice.client.UserService;
import com.emresahna.notificationservice.dto.TransactionEvent;
import com.emresahna.notificationservice.dto.UserNotificationInfo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final UserService userService;

    @KafkaListener(topics = "notify_users")
    public void handleNotifyUsers(TransactionEvent transactionEvent) {
        UserNotificationInfo customerNotifyInfo = userService.getUserNotificationInfoById(transactionEvent.getCustomerId());
        UserNotificationInfo sellerNotifyInfo = userService.getUserNotificationInfoById(transactionEvent.getSellerId());

        String[] emails = {
                customerNotifyInfo.getEmail(),
                sellerNotifyInfo.getEmail()
        };

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emails);
            helper.setSubject("Transaction Notification");

            Context context = new Context();
            context.setVariable("customer_name", customerNotifyInfo.getFullName());
            context.setVariable("seller_name", sellerNotifyInfo.getFullName());
            context.setVariable("amount", transactionEvent.getAmount());
            context.setVariable("createdAt", transactionEvent.getCreatedAt());
            String htmlBody = templateEngine.process("transaction_template", context);
            helper.setText(htmlBody, true);

            mailSender.send(message);
            logger.info(String.format("Transaction notification sent to -> %s and %s", customerNotifyInfo.getEmail(), sellerNotifyInfo.getEmail()));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
