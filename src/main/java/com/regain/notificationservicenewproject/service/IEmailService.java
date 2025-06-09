package com.regain.notificationservicenewproject.service;

import com.regain.notificationservicenewproject.model.MessageDTO;

public interface IEmailService {
    void sendEmail(MessageDTO messageDTO);
}
