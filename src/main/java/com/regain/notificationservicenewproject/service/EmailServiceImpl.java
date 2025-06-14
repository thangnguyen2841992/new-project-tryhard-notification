package com.regain.notificationservicenewproject.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements IEmailService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    @Async
    public void sendEmailActive(String to, String codeActive) {
        String subject = "Kích hoạt tài khoản của bạn tại WatchStore";
        String text = "<br/> Click vào đường link để kích hoạt tài khoản: ";
        String url = "http://localhost:3000/active/" + to +"/" + codeActive;
        text += ("<br/> <a href=" + url + ">" + url + "</a>" );
        sendMailActiveAccount(to, subject, text);
    }

    private  void sendMailActiveAccount(String to, String subject, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper =new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText(text, true);
            helper.setSubject(subject);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        this.javaMailSender.send(message);
    }
}
