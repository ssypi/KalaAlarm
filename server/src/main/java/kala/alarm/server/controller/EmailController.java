package kala.alarm.server.controller;


import kala.alarm.server.model.Email;
import kala.alarm.server.service.EmailService;
import org.glassfish.grizzly.http.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("email")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class EmailController {
    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

    @Inject
    private EmailService emailService;

    @GET
    public List<Email> getEmails(@Context Request request) {
        LOG.info("Email request from " + request.getRemoteAddr());
        return emailService.getEmails();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Email addEmail(Email email) {
        emailService.createEmail(email);

        return email;
    }
}
