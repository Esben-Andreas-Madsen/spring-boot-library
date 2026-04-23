package exception;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.ws.rs.ForbiddenException;

@ApplicationScoped
public class UiExceptionHandler {

    public void handle(Runnable action) {
        try {
            action.run();
        } catch (ForbiddenException e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Access denied"));
        }
    }
}
