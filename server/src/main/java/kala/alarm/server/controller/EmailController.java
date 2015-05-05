package kala.alarm.server.controller;


import kala.alarm.server.model.EmailAddress;
import kala.alarm.server.service.EmailService;
import org.glassfish.grizzly.http.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("email")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class EmailController {
    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

    @Inject
    private EmailService emailService;

    @GET
    public List<EmailAddress> getEmails(@Context Request request) {
        LOG.info("EmailAddress request from " + request.getRemoteAddr());
        return emailService.getEmails();
    }

    @POST
    public Response addEmail(EmailAddress email) {
        emailService.createEmail(email);
        return Response.status(Response.Status.CREATED).entity(email).build();
    }

    @Path("/{id:\\d+}")
    @DELETE
    public Response deleteEmail(@PathParam("id") int id) {
        emailService.deleteEmail(id);
        return Response.status(Response.Status.OK).build();
    }
}
