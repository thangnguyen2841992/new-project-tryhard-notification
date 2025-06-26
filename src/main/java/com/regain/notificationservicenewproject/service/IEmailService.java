package com.regain.notificationservicenewproject.service;

import com.regain.notificationservicenewproject.model.MessageDTO;
import com.regain.notificationservicenewproject.model.MessageNotification;

public interface IEmailService {
    void sendEmailActive(String to, String toName, String codeActive);
    void sendEmailNotification(MessageNotification messageNotification);
}
