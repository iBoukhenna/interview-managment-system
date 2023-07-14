package ca.levio.notification.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ca.levio.notification.config.MailConfigProperties;
import ca.levio.notification.maildtos.InterviewAcceptedMailDataRequestDto;
import ca.levio.notification.maildtos.InterviewAlreadyAcceptedMailDataRequestDto;
import ca.levio.notification.maildtos.InterviewAssignedMailDataRequestDto;
import ca.levio.notification.maildtos.InterviewDeclinedMailDataRequestDto;
import ca.levio.notification.maildtos.MailDataRequestDto;
import ca.levio.notification.maildtos.MailGeneratorResponseDto;
import ca.levio.notification.maildtos.NewInterviewRequestMailDataRequestDto;
import ca.levio.notification.maildtos.NoAvailibleTechnicalAdvisorMailDataRequestDto;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MailGeneratorService {

    private final Configuration configuration;
    private final MailConfigProperties configProperties;

    public MailGeneratorResponseDto generateNewInterviewRequestMailResponseDto(NewInterviewRequestMailDataRequestDto newInterviewRequestMailDataDto) {
        String template = configProperties.getNewInterviewRequestTemplate();
        newInterviewRequestMailDataDto.setSubject(configProperties.getNewInterviewRequestSubject());
        return generateMailResponseDto(newInterviewRequestMailDataDto, template);
    }

    public MailGeneratorResponseDto generateInterviewAcceptedMailResponseDto(InterviewAcceptedMailDataRequestDto interviewAcceptedMailDataDto) {
        String template = configProperties.getInterviewAcceptedTemplate();
        interviewAcceptedMailDataDto.setSubject(configProperties.getInterviewAcceptedSubject());
        return generateMailResponseDto(interviewAcceptedMailDataDto, template);
    }

    public MailGeneratorResponseDto generateInterviewAlreadyAcceptedMailResponseDto(InterviewAlreadyAcceptedMailDataRequestDto interviewAlreadyAcceptedMailDataRequestDto) {
        String template = configProperties.getInterviewAlreadyAcceptedTemplate();
        interviewAlreadyAcceptedMailDataRequestDto.setSubject(configProperties.getInterviewAlreadyAcceptedSubject());
        return generateMailResponseDto(interviewAlreadyAcceptedMailDataRequestDto, template);
    }

    public MailGeneratorResponseDto generateInterviewAssignedMailResponseDto(InterviewAssignedMailDataRequestDto interviewAssignedMailDataRequestDto) {
        String template = configProperties.getInterviewAssignedTemplate();
        interviewAssignedMailDataRequestDto.setSubject(configProperties.getInterviewAssignedSubject());
        return generateMailResponseDto(interviewAssignedMailDataRequestDto, template);
    }

    public MailGeneratorResponseDto generateInterviewDeclinedMailResponseDto(InterviewDeclinedMailDataRequestDto interviewDeclinedMailDataRequestDto) {
        String template = configProperties.getInterviewDeclinedTemplate();
        interviewDeclinedMailDataRequestDto.setSubject(configProperties.getInterviewDeclinedSubject());
        return generateMailResponseDto(interviewDeclinedMailDataRequestDto, template);
    }

    public MailGeneratorResponseDto generateNoAvailibleTechnicalAdvisorMailResponseDto(NoAvailibleTechnicalAdvisorMailDataRequestDto noAvailibleTechnicalAdvisorMailDataRequestDto) {
        String template = configProperties.getNoAvailibleTechnicalAdvisorTemplate();
        noAvailibleTechnicalAdvisorMailDataRequestDto.setSubject(configProperties.getNoAvailibleTechnicalAdvisorSubject());
        return generateMailResponseDto(noAvailibleTechnicalAdvisorMailDataRequestDto, template);
    }

    private MailGeneratorResponseDto generateMailResponseDto(MailDataRequestDto mailDataDto, String template) {
        MailGeneratorResponseDto mailGeneratorResponseDto = null;
        try {
            StringWriter stringWriter = new StringWriter();
            Map<String, Object> model = new HashMap<>();
            model.put("mailDataDto", mailDataDto);
            configuration.getTemplate(template).process(model, stringWriter);
            String content = stringWriter.getBuffer().toString();
            mailGeneratorResponseDto = new MailGeneratorResponseDto(mailDataDto.getSubject(), content, mailDataDto.getTo());
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return mailGeneratorResponseDto;
    }
}
