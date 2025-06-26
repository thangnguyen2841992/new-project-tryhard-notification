package com.regain.notificationservicenewproject.service;


import com.regain.notificationservicenewproject.model.MessageNotification;
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
    public void sendEmailActive(String to, String toName, String codeActive) {
        String subject = "Kích hoạt tài khoản của bạn tại WatchStore";
        String text = "<br/> Xin chào" + toName + "<br/> Click vào đường link để kích hoạt tài khoản: ";
        String url = "http://localhost:3000/active/" + to + "/" + codeActive;
        text += ("<br/> <a href=" + url + ">" + url + "</a>");
        sendMailActiveAccount(to, subject, text);
    }


    @Override
    @Async
    public void sendEmailNotification(MessageNotification messageNotification) {
        String subject = "Thông Báo Của Bạn!";
        String text = "<br/> Xin Chào  <b>" + messageNotification.getToName() + "</b><br/>";
        String url = "http://localhost:3000/about/" + messageNotification.getFormUserId();
        String content = "";
        switch (messageNotification.getTypeNotification()) {
            case  1 : {
                content += " đã yêu thích bài viết ";
                break;
            }
            case  2 : {
                content += " đã để lại bình luận ở bài viết ";
                break;
            }
            case  3 : {
                content += " đã yêu thích bình luận của bạn ở bài viết ";
                break;
            }
            case  4 : {
                content += " đã phản hồi bình luận của bạn ở bài viết ";
                break;
            }
        }
        if (messageNotification.getTypeNotification() == 1) {
            text += ("<br/> <a href=" + url + ">" + "<b>" + messageNotification.getFormName() + "</b>" + content + "<b>" + messageNotification.getPostTitle()+ " !" + "</a>");
        } else {
            text += ("<br/> <a href=" + url + ">" + "<b>" + messageNotification.getFormName() + "</b>" + content + "<b>" + messageNotification.getPostTitle()+ " !" + "</a>");
            text += ("<br/><b>Nội dung: </b><p>" + messageNotification.getContent() + "</p>" );
        }
        text += ("<b>Chúc bạn 1 ngày vui vẻ!</b>" );
        sendMailActiveAccount(messageNotification.getTo(), subject, text);

    }

    private void sendMailActiveAccount(String to, String subject, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
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
