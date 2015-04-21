package kala.alarm.server.controller;

import kala.alarm.server.model.AppError;
import kala.alarm.server.service.ErrorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.glassfish.grizzly.http.server.Request;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("error")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
public class ErrorController {
    private static final Logger LOG = LoggerFactory.getLogger(ErrorController.class);

    @Inject
    private ErrorService errorService;

    @GET
    public List<AppError> getErrors(@Context Request request) {
        LOG.info("Error request from " + request.getRemoteAddr());
        return errorService.getErrors();
    }
}
