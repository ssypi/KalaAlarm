package kala.alarm.server.controller;

import org.glassfish.grizzly.utils.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionMapper.class);

    @Override
    public Response toResponse(Throwable exception) {
        LOG.error("Error during http request", exception);
        return Response
                .status(500)
                .entity(Exceptions.getStackTraceAsString(exception))
                .type("text/plain")
                .build();
    }
}
