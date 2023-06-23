package ca.levio.notification.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ca.levio.commonbean.messageevent.NotificationMessageEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MailNotificationService implements NotificationService {
    
    private final JavaMailSender javaMailSender;

    public void send(NotificationMessageEvent notificationMessageEvent) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject(notificationMessageEvent.getSubject());
            helper.setTo(notificationMessageEvent.getTo());
            helper.setText(notificationMessageEvent.getContent(), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("mail non envoyé");
        }
    }
}
