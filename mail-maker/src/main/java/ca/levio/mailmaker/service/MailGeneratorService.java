package ca.levio.mailmaker.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ca.levio.mailmaker.config.ConfigProperties;
import ca.levio.mailmaker.maildtos.MailDataRequestDto;
import ca.levio.mailmaker.maildtos.MailGeneratorResponseDto;
import ca.levio.mailmaker.maildtos.NewInterviewRequestMailDataRequestDto;
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
