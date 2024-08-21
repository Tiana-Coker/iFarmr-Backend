package org.ifarmr.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ifarmr.payload.request.EmailDetails;
import org.ifarmr.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Override
    public void sendEmailAlerts(EmailDetails emailDetails, String templateName) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Context context = new Context();
        Map<String, Object> variables = Map.of(
                "name", emailDetails.getFullName(),
                "link", emailDetails.getLink()

        );
        context.setVariables(variables);

        messageHelper.setFrom(senderEmail);
        messageHelper.setTo(emailDetails.getRecipient());
        messageHelper.setSubject(emailDetails.getSubject());

        String html = templateEngine.process(templateName, context);
        messageHelper.setText(html, true);

        javaMailSender.send(message);
        log.info("Sending email: to {}", emailDetails.getRecipient());

    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

        try {
            messageHelper.setFrom(senderEmail);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(body, false); // false indicates plain text email
            javaMailSender.send(message);
            log.info("Sent plain text email to {}", to);
        } catch (MessagingException e) {
            log.error("Failed to send plain text email", e);
            throw new RuntimeException("Failed to send plain text email", e);
        }
    }

}
