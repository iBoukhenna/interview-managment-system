package ca.levio.notification.maildtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MailGeneratorResponseDto {
    private String subject;
    private String content;
    private String to;
}
