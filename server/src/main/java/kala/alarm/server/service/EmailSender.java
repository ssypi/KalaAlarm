package kala.alarm.server.service;

import kala.alarm.server.model.EmailAddress;
import kala.alarm.server.model.EmailMessage;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.util.List;
import java.util.Set;

/**
 * Created by Jussi on 5.5.2015.
 */
public class EmailSender {

    private String userName = "Teuvotestaaja123@gmail.com";
    private String userPassword = "testaaja";



    public void sendEmail(EmailMessage emailMessage, Set<EmailAddress> recipientList){

        Email email = new SimpleEmail();

        try {

            email.setHostName("smtpcorp.com");
            email.setSmtpPort(2525);
            //email.setSslSmtpPort("587");
            email.setAuthenticator(new DefaultAuthenticator(userName, userPassword));
            //email.setSSLOnConnect(true);
            email.setFrom(userName);
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
