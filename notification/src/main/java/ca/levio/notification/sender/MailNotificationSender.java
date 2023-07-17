package ca.levio.notification.sender;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ca.levio.notification.dto.NotificationDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MailNotificationSender implements NotificationSender {
    
    private final JavaMailSender javaMailSender;

    public void send(NotificationDto notificationDto) {
        //System.out.println(notificationDto.getSubject() + " toooooooooo " + notificationDto.getTo() + " this contenttttttttttt " + notificationDto.getContent());
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject(notificationDto.getSubject());
            helper.setTo(notificationDto.getTo());
            helper.setText(notificationDto.getContent(), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println("mail non envoyé");
        }
    }
}
