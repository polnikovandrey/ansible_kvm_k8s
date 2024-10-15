package com.mcfly.mailer.service;

import com.mcfly.mailer.payload.queue.EmailConfirmationEvent;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaMessagingService {

    private static final String topicCreateOrder = "${topic.email-confirmation}";
    private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";

    private final MailService mailService;

    @KafkaListener(
            topics = topicCreateOrder,
            groupId = kafkaConsumerGroupId,
            properties = {"spring.json.value.default.type=com.mcfly.mailer.payload.queue.EmailConfirmationEvent"}
    )
    public EmailConfirmationEvent createOrder(EmailConfirmationEvent emailConfirmationEvent) throws MessagingException {
        log.info("Message consumed {}", emailConfirmationEvent);
        mailService.sendEmailConfirmation(emailConfirmationEvent.getEmail(), emailConfirmationEvent.getUrl());
        return emailConfirmationEvent;
    }
}