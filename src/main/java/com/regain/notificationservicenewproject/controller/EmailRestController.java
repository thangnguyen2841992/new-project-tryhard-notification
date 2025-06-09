package com.regain.notificationservicenewproject.controller;

import com.regain.notificationservicenewproject.model.MessageDTO;
import com.regain.notificationservicenewproject.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailRestController {
    @Autowired
    private IEmailService emailService;

    @PostMapping("/sendNotificationEmail.do")
    public void sendNotificationEmail(@RequestBody MessageDTO messageDTO) {
        emailService.sendEmail(messageDTO);
    }
}
