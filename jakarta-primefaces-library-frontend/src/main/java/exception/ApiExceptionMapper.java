package exception;

import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

public class ApiExceptionMapper implements ResponseExceptionMapper<RuntimeException> {

    @Override
    public RuntimeException toThrowable(Response response) {

        int status = response.getStatus();

        return switch (status) {
            case 401 -> new NotAuthorizedException("Unauthorized");
            case 403 -> new ForbiddenException();
            case 404 -> new NotFoundException();
            default -> new RuntimeException("API error: " + status);
        };
    }
}
