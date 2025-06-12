package com.regain.notificationservicenewproject.service;

import com.regain.notificationservicenewproject.model.MessageDTO;

public interface IEmailService {
    void sendEmailActive(String to, String toName, String codeActive);
}
