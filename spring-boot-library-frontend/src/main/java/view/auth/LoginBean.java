package view.auth;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Named
@RequestScoped
public class LoginBean {

    public void login() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) ec.getSession(true);

            String redirectUri = ec.getRequestScheme() + "://"
                    + ec.getRequestServerName() + ":"
                    + ec.getRequestServerPort()
                    + ec.getRequestContextPath() + "/callback";

            // --- PKCE ---
            byte[] code = new byte[32];
            new SecureRandom().nextBytes(code);
            String codeVerifier = Base64.getUrlEncoder().withoutPadding().encodeToString(code);
            session.setAttribute("code_verifier", codeVerifier);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(codeVerifier.getBytes(StandardCharsets.US_ASCII));
            String codeChallenge = Base64.getUrlEncoder().withoutPadding().encodeToString(hash);

            // --- Build URL ---
            String authUrl =
                    "http://host.docker.internal:1852/realms/library/protocol/openid-connect/auth"
                            + "?client_id=jakarta-frontend"
                            + "&response_type=code"
                            + "&scope=openid"
                            + "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8)
                            + "&code_challenge=" + URLEncoder.encode(codeChallenge, StandardCharsets.UTF_8)
                            + "&code_challenge_method=S256";

            ec.redirect(authUrl);

        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void logout() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.invalidateSession();
            ec.redirect(ec.getRequestContextPath() + "/login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
