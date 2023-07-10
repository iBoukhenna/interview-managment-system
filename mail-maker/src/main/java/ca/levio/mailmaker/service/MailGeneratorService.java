package ca.levio.mailmaker.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ca.levio.mailmaker.config.ConfigProperties;
import ca.levio.mailmaker.maildtos.InterviewAcceptedMailDataRequestDto;
import ca.levio.mailmaker.maildtos.InterviewAlreadyAcceptedMailDataRequestDto;
import ca.levio.mailmaker.maildtos.InterviewAssignedMailDataRequestDto;
import ca.levio.mailmaker.maildtos.InterviewDeclinedMailDataRequestDto;
import ca.levio.mailmaker.maildtos.MailDataRequestDto;
import ca.levio.mailmaker.maildtos.MailGeneratorResponseDto;
import ca.levio.mailmaker.maildtos.NewInterviewRequestMailDataRequestDto;
import ca.levio.mailmaker.maildtos.NoAvailibleTechnicalAdvisorMailDataRequestDto;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MailGeneratorService {

    private final Configuration configuration;
    private final ConfigProperties configProperties;

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
