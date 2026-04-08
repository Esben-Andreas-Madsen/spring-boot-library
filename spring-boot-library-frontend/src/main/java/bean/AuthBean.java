package bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Named
@RequestScoped
public class AuthBean {

    public void logout() throws Exception {
        HttpServletRequest request =
                (HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequest();

        request.logout();

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect(FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequestContextPath() + "/index.xhtml");
    }

    public void login() throws Exception {
        HttpServletRequest request =
                (HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequest();

        HttpServletResponse response =
                (HttpServletResponse) FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getResponse();

        request.authenticate(response); // triggers OIDC
    }
}