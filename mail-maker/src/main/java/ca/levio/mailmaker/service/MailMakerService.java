package ca.levio.mailmaker.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ca.levio.mailmaker.bean.NewInterviewRequestMail;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;

import ca.levio.commonbean.messageevent.NotificationMessageEvent;

@AllArgsConstructor
@Service
public class MailMakerService {
    
    private final Configuration configuration;

    public NotificationMessageEvent getNotificationContent(NewInterviewRequestMail newInterviewRequestMail) {
        NotificationMessageEvent notificationMessageEvent = null;
        try {
            StringWriter stringWriter = new StringWriter();
            Map<String, Object> model = new HashMap<>();
            model.put("newInterviewRequestMail", newInterviewRequestMail);
            configuration.getTemplate("new-interview-request.ftlh").process(model, stringWriter);
            String content = stringWriter.getBuffer().toString();
            String subject = "Nouvelle demande d'entrevue technique";
            notificationMessageEvent = new NotificationMessageEvent(subject, content, newInterviewRequestMail.getTo());
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return notificationMessageEvent;
    }
}
