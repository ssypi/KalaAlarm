package kala.alarm.server.controller;

import kala.alarm.server.model.Application;
import kala.alarm.server.model.Email;
import kala.alarm.server.service.ApplicationService;
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

@Path("application")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class ApplicationController {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationController.class);

    @Inject
    private ApplicationService applicationService;

    @GET
    public List<Application> getApplications(@Context Request request) {
        LOG.info("Application request from " + request.getRemoteAddr());
        List<Application> applications = applicationService.getApplications();
        return applications;
    }

    @Path("/{id:\\d+}/subscribers")
    @GET
    public List<Email> getSubscribers(@PathParam("id") int applicationId) {
        return applicationService.getSubscribers(applicationId);
    }

    @POST
    public Response addApplication(Application application) {
        applicationService.addApplication(application);
        return Response.status(Response.Status.CREATED).entity(application).build();
    }
}
