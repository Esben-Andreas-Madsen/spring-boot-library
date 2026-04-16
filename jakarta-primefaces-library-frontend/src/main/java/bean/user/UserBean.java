package bean.user;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.security.Principal;

import jakarta.faces.context.FacesContext;

@Named
@SessionScoped
public class UserBean implements Serializable {

    public Principal getPrincipal() {
        return FacesContext.getCurrentInstance()
                .getExternalContext()
                .getUserPrincipal();
    }

    public String getUsername() {
        Principal p = getPrincipal();
        return p != null ? p.getName() : null;
    }

    public boolean isLoggedIn() {
        return getPrincipal() != null;
    }

}
