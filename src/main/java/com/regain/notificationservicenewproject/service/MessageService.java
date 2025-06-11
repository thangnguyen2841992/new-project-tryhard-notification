package com.regain.notificationservicenewproject.service;

import com.regain.notificationservicenewproject.model.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IEmailService emailService;

    @KafkaListener(id="sendEmailActiveGroup", topics = "send-email-active")
    public void receiveEmailActive(MessageDTO messageDTO ) {
        logger.info("Received email: {}", messageDTO.getTo());
        emailService.sendEmailActive(messageDTO.getTo(), messageDTO.getActiveCode());
    }
}
