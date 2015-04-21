package kala.alarm.server.service;

import kala.alarm.server.model.AppError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
@Path("service-error")
public class ErrorService {
    private static final Logger LOG = LoggerFactory.getLogger(ErrorService.class);

    // TODO: use database, not an in-memory list
    private List<AppError> errors = new ArrayList<AppError>() {{
        add(new AppError("Super error", "From super app"));
        add(new AppError("Super error2", "From super app2"));
    }};

    public void createError(AppError error) {
        LOG.debug("Error from {}, message: {}", error.getOrigin(), error.getMessage());
        errors.add(error);
    }

    public List<AppError> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}
