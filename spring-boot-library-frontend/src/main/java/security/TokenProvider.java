package security;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;

@RequestScoped
public class TokenProvider {

    public String getToken() {
        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false);

        if (session == null) return null;

        String token = (String) session.getAttribute("access_token");
        return token;
    }
}
