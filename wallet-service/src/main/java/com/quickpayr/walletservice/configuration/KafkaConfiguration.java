package com.quickpayr.walletservice.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {
    @Bean
    public NewTopic topicForDecrementSuccess() {
        return new NewTopic("decrement_success", 1, (short) 1);
    }

    @Bean
    public NewTopic topicForDecrementRollback() {
        return new NewTopic("decrement_rollback", 1, (short) 1);
    }

    @Bean
    public NewTopic topicForProductAvailabilitySuccess() {
        return new NewTopic("product_availability_success", 1, (short) 1);
    }
}
