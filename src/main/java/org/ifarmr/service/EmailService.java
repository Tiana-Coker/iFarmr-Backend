package org.ifarmr.service;

import jakarta.mail.MessagingException;
import org.ifarmr.payload.request.EmailDetails;

public interface EmailService {
    void sendEmailAlerts(EmailDetails emailDetails, String templateName) throws MessagingException;
    void sendEmail(String to, String subject, String body);

}
