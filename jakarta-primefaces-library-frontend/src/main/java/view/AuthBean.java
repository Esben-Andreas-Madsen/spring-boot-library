package view;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Named
@RequestScoped
public class AuthBean {

    public void logout() throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest();

        request.logout(); // ends session + OIDC token
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect(request.getContextPath() + "/index.xhtml");
    }
}