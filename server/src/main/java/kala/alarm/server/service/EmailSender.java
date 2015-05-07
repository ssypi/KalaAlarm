package kala.alarm.server.service;

import kala.alarm.server.model.EmailAddress;
import kala.alarm.server.model.EmailMessage;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Set;

public class EmailSender {
    private static Logger LOG = LoggerFactory.getLogger(EmailSender.class);

    private static final String EMAIL_CONFIG_FILE = "email.properties";

    private final String smtpAddress;
    private final String senderAddress;
    private final String smtpUser;
    private final String smtpPassword;
    private final int smtpPort;


    public EmailSender() {
        ConfigReader configReader = new ConfigReader(EMAIL_CONFIG_FILE);
        senderAddress = configReader.readString("EMAIL_ADDRESS", null);
        smtpUser = configReader.readString("SMTP_USER", null);
        smtpAddress = configReader.readString("SMTP_SERVER", null);
        smtpPassword = configReader.readString("SMTP_PASSWORD", null);
        smtpPort = configReader.readInt("SMTP_PORT", 0);
    }

    public void sendEmail(EmailMessage emailMessage, Set<EmailAddress> recipientList) {
        if (recipientList == null || recipientList.isEmpty()) {
            LOG.debug("No recipients for email " + emailMessage.getSubject());
            return;
        }

        Email email = new SimpleEmail();

        try {
            email.setHostName(smtpAddress);
            email.setSmtpPort(smtpPort);
            //email.setSslSmtpPort("587");
            email.setAuthenticator(new DefaultAuthenticator(smtpUser, smtpPassword));
            //email.setSSLOnConnect(true);
            email.setFrom(senderAddress);
            email.setSubject(emailMessage.getSubject());
            email.setMsg(emailMessage.getBody());

            recipientList.forEach(emailAddress -> {
                try {
                    email.addTo(emailAddress.getAddress());
                } catch (EmailException e) {
                    e.printStackTrace();
                }
            });

            //email.setStartTLSEnabled(true);
            email.send();

        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
