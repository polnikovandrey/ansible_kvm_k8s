package com.mcfly.template.service;

import com.mcfly.template.payload.queue.EmailConfirmationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessagingService {

    @Value("${topic.email-confirmation}")
    private String sendEmailConfirmationTopic;

    private final KafkaTemplate<String , Object> kafkaTemplate;

    public void sendEmailConfirmation(EmailConfirmationEvent emailConfirmationEvent) {
        kafkaTemplate.send(sendEmailConfirmationTopic, emailConfirmationEvent.getUrl(), emailConfirmationEvent);
    }
}